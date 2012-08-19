package com.wcs.showcase.crud.service;

import static junit.framework.Assert.assertTrue;

import java.util.List;

import javax.ejb.EJB;

import org.junit.Test;

import com.wcs.commons.security.model.Role;
import com.wcs.commons.security.model.User;
import com.wcs.commons.security.service.LoginService;

/**
/**
 * <p>Project: btcbase-web</p> 
 * <p>Title: </p> 
 * <p>Description: </p> 
 * <p>Copyright: Copyright 2011-2020.All rights reserved.</p> 
 * <p>Company: wcs.com</p> 
 * @author guanjianghuai
 */
public class LoginServiceTest extends BaseTest{

	@EJB
	LoginService loginService;

	@Test
	public void test_findUser(){
		User user = loginService.findUser(1L);
	}
	
	@Test
	public void test_findUser2(){
		User user = loginService.findUser("guanjianghuai");
	}
	

    /**
     * <b>案例:</b> search() 查询人员信息 <br/> 
     * [前提条件]<br/>
     * [输入参数]<br/> 
     * [预期输出]人员信息列表<br/>
     * [预期异常]<br/>
     */
	@Test
	public void test_findRoles(){
		List<Role> list = loginService.findRoles("guanjianghuai");
		assertTrue( list.size() >=0 );
	}
	
}
