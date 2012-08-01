/**
 * DictService.java
 * Created: 2012-2-16 下午01:37:43
 */
package com.wcs.common.service;

import java.util.*;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import com.wcs.common.model.Dict;

/**
 * <p>Project: tih</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2012 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:chengchao@wcs-global.com">ChengChao</a>
 * 注解Starup,启动程序就立即加载.
 */

@Stateless
public class CommonService {
	
	//查询所有语言类 loadDict()使用
	private List<String> langList; 
	
	//语言分类的list MAP集合,开始程序就就获取到. loadDict()得到
	private Map<String, List<Dict>> langKeyDictMap=new  HashMap<String, List<Dict>>();

	//数据库中所有的数据,根据JPQL查出,总的数据
	private List<Dict> dicts=new ArrayList<Dict>();
	
	//专门用于查询getValueByDictCatKey的Value值的map集合.
	private Map<String,String> getValueMap=new HashMap<String, String>();
	
	@PersistenceContext 
	public EntityManager em;

    private ResourceBundle res;
	
	/**
	 * <p>Description: 系统加载时查询DICT表，将所有defunct_ind!='Y'的记录放到map,key=CODE_CAT+"."+CODE_KEY,value=CODE+VAL并放到application级别的bean中。</p>
	 * @return
	 */
	@PostConstruct
	public void loadDict() {
		this.queryDict();
	}
	
	/**
	 * <p>Description: 一系列的取值,刷新时以及程序刚运行时调用这个方法</p>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void queryDict(){
		//查询出数据库中一共有几种语言
		String langSQL="SELECT distinct lang FROM Dict";
		langList =em.createNativeQuery(langSQL.toString()).getResultList();
		
		//循环查询出每种语言的所有数据,然后装如一个MAP集合中去,以语言类型为key,value为所有的数据是一个list集合.
		for(int i=0;i<langList.size();i++){
			String strJPQL="SELECT d FROM Dict d where d.defunctInd <> 'Y' and d.lang = '"+langList.get(i)+"' order by d.lang,d.codeCat,d.seqNo";
		//	String strSQL="SELECT * FROM DICT WHERE defunct_ind <> 'Y' and lang='"+langList.get(i)+"' order by code_cat,seq_NO";
			//用原生SQL查询的是List<Object>类型.而JPQL是List<Dict>类型.
			List<Dict> result=em.createQuery(strJPQL).getResultList();
			langKeyDictMap.put( langList.get(i).toString(),result);
		}
		
		String allJQPL="SELECT d FROM Dict d";
		List<Dict> allResult=em.createQuery(allJQPL).getResultList();
		for(int l=0;l<allResult.size();l++){
			//查询出所有的数据,以cat_point_key_lang为key,Value为value.Map形式存储,在取值时效率高.
			String keyData=(allResult.get(l).getCodeCat().toString()+"."+allResult.get(l).getCodeKey().toString()+"."+allResult.get(l).getLang().toString()).replace(".", "_");
			getValueMap.put(keyData, allResult.get(l).getCodeVal().toString());
		}

	}
	
	/**
	 * <p>Description: 从application级别的bean获取该值，不要直接从数据库获取
	 * 这里是以d.getCodeCat()+"."+d.getCodeKey()来dictMap中获取相应的value值.
	 * </p>
	 * @param cat_point_key_lang
	 * @return
	 */
	public String getValueByDictCatKey(String cat_point_key_lang) {
		String codeValue=getValueMap.get(cat_point_key_lang);
		return codeValue;
	}
//	public String getValueByDictCatKey(String cat_point_key,String lang) {
//		//根据浏览器语言环境选取langKeyMap中的 List<Dict>集合.
//		dicts=langKeyDictMap.get(lang);
//		//转换为MAP形式
//		String valueByDouble=null;
//		for( int i=0;i<dicts.size();i++){
//			String variable=(dicts.get(i).getCodeCat()+"."+dicts.get(i).getCodeKey()).toString();
//			if(variable.equals(cat_point_key)){
//				valueByDouble=dicts.get(i).getCodeVal();
//				return valueByDouble;
//			}
//		}
//		return null;
//	}

	/**
	 * <p>Description: 根据cat值获得所有的Dict列表</p>
	 * @param codeCat
	 * @param lang
	 * @return
	 */
	public List<Dict> getDictByCat(String codeCat,String lang) {
		//根据浏览器语言环境选取langKeyMap中的 List<Dict>集合.
		dicts=langKeyDictMap.get(lang);
        List<Dict> listByCat=new ArrayList<Dict>();

        if(null == dicts){
            return listByCat;
        }

		for( int i=0;i<dicts.size();i++){
			if(dicts.get(i).getCodeCat().equals(codeCat)){
				listByCat.add(dicts.get(i));
			}
		}
		//返回结果.
		return listByCat;
	}

    public String getMessage(String key){
        //初始化国际化资源环境
        FacesContext context = FacesContext.getCurrentInstance();
        res = context.getApplication().getResourceBundle(context,"msgs");
        return this.res.getString(key);
    }
}
