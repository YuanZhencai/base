package com.wcs.showcase.crud.service;

import java.util.List;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.junit.Test;

import com.wcs.base.security.model.Resource;
import com.wcs.base.security.model.Role;
import com.wcs.base.security.model.RoleResource;
import com.wcs.base.security.model.User;
import com.wcs.base.security.service.LoginService;

/**
/**
 * <p>Project: btcbase-security</p> 
 * <p>Title: </p> 
 * <p>Description: </p> 
 * <p>Copyright: Copyright 2011-2020.All rights reserved.</p> 
 * <p>Company: wcs.com</p> 
 * @author guanjianghuai
 */
public class LoginServiceTest extends BaseTest{

	@EJB
	private LoginService loginService;
	
    /**
     * <b>案例:</b> search() 查询人员信息 <br/> 
     * [前提条件]<br/>
     * [输入参数]<br/> 
     * [预期输出]人员信息列表<br/>
     * [预期异常]<br/>
     */
	@Test
	public void test_findAllSysResource(){
		List<Resource> list = loginService.findAllSysResource();
		Assert.assertTrue( list.size() >=0 );
	}
	
	@Test
	public void test_findUser(){
		User user = loginService.findUser("guanjianghuai");
	}
	
	@Test
	public void test_findPermissions(){
		Role role = new Role();
		role.setId(10L);
		List<RoleResource> list = loginService.findPermissions(role);
		Assert.assertTrue( list.size() >=0 );
	}
	
}
