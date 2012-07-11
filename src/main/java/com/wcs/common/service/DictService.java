package com.wcs.common.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wcs.common.controller.vo.DictVO;
import com.wcs.common.model.Dict;

@Stateless
public class DictService {
	
	@PersistenceContext 
	public EntityManager em;
	
	//查询,模糊条件有"类别","键","值","系统标识","语言"五个.
	public List<DictVO> searchData(String codeCat,String codeKey,String codeVal,String sysInd, String lang){
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT d FROM Dict d WHERE d.defunctInd='N' ");
		if(codeCat!="" && codeCat!= null){
			strSql.append(" AND d.codeCat LIKE '%" + codeCat + "%' ");
		}
		if(codeKey!="" && codeKey!= null){
			strSql.append(" AND d.codeKey LIKE '%" + codeKey + "%' ");
		}
		if(codeVal!="" && codeVal!= null){
			strSql.append(" AND d.codeVal LIKE '%" + codeVal + "%' ");
		}
		if(sysInd!="" && sysInd!= null){
			strSql.append(" AND d.sysInd LIKE '%" + sysInd + "%' ");
		}
		if(lang !="" && lang != null){
			strSql.append(" AND d.lang LIKE '%" + lang + "%' ");
		}
		Query query=em.createQuery(strSql.toString());
		@SuppressWarnings("unchecked")
		List<Dict> result=query.getResultList();
		List<DictVO> list=new ArrayList<DictVO>();
		for(Dict d: result){
			list.add(new DictVO(d.getId(), d.getCodeCat(), d.getCodeKey(), d.getCodeVal(), d.getRemarks(), d.getSeqNo(), d.getSysInd(), d.getLang(), d.getDefunctInd()));
		}
		return list;
	}//end
	
	//添加,顺序为"类别","键","值","顺序号","语言","系统标识","是否生效","备注".共8个参数.
	public void insertData(Dict dict){
		this.em.persist(dict);
	}
	
	//编辑
	public void updateData(DictVO selectData,String updateUser){
		StringBuilder strSql=new StringBuilder("UPDATE Dict d ");
		strSql.append("SET d.codeCat = '"+ selectData.getCodeCat() +"',");
		strSql.append("d.codeKey = '"+ selectData.getCodeKey() +"',");
		strSql.append("d.codeVal = '"+ selectData.getCodeVal() +"',");
		strSql.append("d.seqNo = '"+ selectData.getSeqNo() +"',");
		strSql.append("d.lang = '"+ selectData.getLang() +"',");
		strSql.append("d.sysInd = '"+ selectData.getSysInd() +"',");
		strSql.append("d.defunctInd = '"+ selectData.getDefunctInd() +"',");
		strSql.append("d.remarks = '"+ selectData.getRemarks() +"',");
		strSql.append("d.updatedBy = '"+ updateUser +"',");
		strSql.append("d.updatedDatetime = '"+ new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").format(new Date()) +"'");
		strSql.append(" WHERE d.id=" + (int)selectData.getId() + " ");
		this.em.createQuery(strSql.toString()).executeUpdate();
	}
	
	
	
	
	
	
}
