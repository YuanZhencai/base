package com.wcs.commons.security.service;

import java.util.Map;

import javax.ejb.EJB;

import org.junit.Test;
import org.primefaces.model.LazyDataModel;

import com.google.common.collect.Maps;
import com.wcs.base.test.BaseTest;
import com.wcs.commons.security.model.User;

/**
 * 
 * @author Chris Guan
 */
public class UserServiceTest extends BaseTest{

	@EJB
	private UserService userService;
	
    /**
     * <b>案例:</b> search() 查询人员信息 <br/> 
     * [前提条件]<br/>
     * [输入参数]<br/>
     * [预期输出]人员信息列表<br/>
     * [预期异常]<br/>
     */
	@Test
	public void test_findUsers(){
		Map<String, Object> filterMap = Maps.newHashMapWithExpectedSize(4);
		filterMap.put("LIKES_adAccount", "fnadmin");
		filterMap.put("LIKES_nachn", "");
		filterMap.put("LIKES_telno", "");
		
		LazyDataModel<User> list = userService.findUsers(filterMap);
		System.out.println("UserServiceTest=>test_findUsers() : "+list.getRowCount());
		
	}
	
}
