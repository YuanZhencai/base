package com.wcs.showcase.crud.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;

import com.wcs.base.service.StatelessEntityService;
import com.wcs.showcase.crud.model.Person;

/** 
* <p>Project: btcbase</p> 
* <p>Title: PersonService.java</p> 
* <p>Description: </p> 
* <p>Copyright: Copyright 2011.All rights reserved.</p> 
* <p>Company: wcs.com</p> 
* @author <a href="mailto:yangshiyun@wcs-global.com">Yang Shiyun</a> 
*/

@Stateless
public class PersonService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private StatelessEntityService entityService;

    /**
     * 查询所有的人员信息
     * @return List<Person>
     */
    public List<Person> search() {
    	List<Person> list = entityService.findAll(Person.class);
    	
		return  list;	
	}
    
    /**
     * 删除指定人员信息
     * @param id
     */
    public void delete(Long id){
    	List<Person> list = entityService.findList("selet p from Person p where id = " + id);
    	if(list != null && list.size()>0){
    		entityService.delete(list.get(0));
    	}
    	
    	/*int j=0;
		List<Person> list = search();
		System.out.println("************总共： "+list.size()+" 条数据************");
		for(int i=0;i<list.size();i++){
			if(list.get(i).getId()<10){
				entityService.delete(list.get(i));
				j++;
			}
		}
		System.out.println("************已删除："+j+" 条数据************");
		List<Person> list1 = search();
		System.out.println("************剩余： "+list1.size()+" 条数据************");*/
    }

    /**
     * 动态分页， XSQL 查询 （推荐使用）
     * @param filterMap
     * @return LazyDataModel<Person>
     */
	public LazyDataModel<Person> findModelByMap(Map<String, Object> filterMap) {
		String hql = "select p from Person p where p.defunctInd = false";
		StringBuilder xsql =  new StringBuilder(hql);
	    xsql.append(" /~ and p.name like {name} ~/ ")
	        .append(" /~ and p.sex like {sex} ~/ ")
	        .append(" /~ and p.birthday >= {startBirthday} ~/")
	        .append(" /~ and p.birthday <= {endBirthday} ~/");
	    return entityService.findXsqlPage(xsql.toString(), filterMap);
	}

	//-------------------- setter & getter --------------------//
	
	public void setEntityService(StatelessEntityService entityService) {
		this.entityService = entityService;
	}
	
}
