package com.wcs.common.service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.wcs.common.controller.vo.DictVO;
import com.wcs.common.model.Dict;
import com.wcs.common.util.Deployments;

/** 
* <p>Project: btcbase</p> 
* <p>Title: DictServiceTest.java</p> 
* <p>Description: </p> 
* <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
* <p>All Rights Reserved.</p>
* @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a> 
*/
@RunWith(Arquillian.class)
public class DictServiceTest {

	@Deployment
	public static Archive<?> createDeployment() {
		WebArchive webArchive = Deployments.createDeployment();
		System.out.println(webArchive.toString(true));
		return webArchive;
	}

	@EJB
	private DictService dictService;

	/**
	 * <b>案例:</b> insertData((Dict dict) 添加数据字典 <br/>
	 * [前提条件]<br/>
	 * [输入参数]Dict对象<br/>
	 * [预期输出]向Dict表添加一条数据<br/>
	 * [预期异常]<br/>
	 */
	@Test
	public void testInsertData() {
		System.out.println("----------测试添加数据字典<开始>---------");
		Dict d = new Dict();
		d.setCodeCat("性别");
		d.setCodeKey("XB");
		d.setCodeVal("性别");
		d.setDefunctInd("N");
		d.setLang("zh_CN");
		d.setRemarks("");
		d.setSeqNo(0l);
		d.setSysInd("Y");
		d.setCreatedBy("test");
		d.setCreatedDatetime(new Date());
		d.setUpdatedBy("test");
		d.setUpdatedDatetime(new Date());
		dictService.insertData(d);
		System.out.println("----------测试添加数据字典<结束>---------");
	}

	/**
	 * <b>案例:</b> searchData(String codeCat,String codeKey,String codeVal,String sysInd, String lang) 查询数据字典 <br/>
	 * [前提条件]<br/>
	 * [输入参数]"类别","键","值","系统标识","语言"<br/>
	 * [预期输出]数据字典列表<br/>
	 * [预期异常]<br/>
	 */
	@Test
	public void testSearchData() {
		System.out.println("----------测试查询数据字典<开始>---------");
		List<DictVO> list = dictService.searchData(null, null, null, null, null);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getCodeVal());
		}
		System.out.println("----------测试查询数据字典<结束>---------");
	}

	/**
	 * <b>案例:</b> updateData(DictVO selectData,String updateUser) 修改数据字典 <br/>
	 * [前提条件]<br/>
	 * [输入参数]"DictVO对象","修改人"<br/>
	 * [预期输出]修改数据字典<br/>
	 * [预期异常]<br/>
	 */
	@Test
	public void testUpdateData() {
		System.out.println("----------测试更新数据字典<开始>---------");

		List<DictVO> list1 = dictService.searchData("性别", "XB", null, null, null);
		for (int i = 0; i < list1.size(); i++) {
			System.out.println("更新前：" + list1.get(i).getCodeVal());
		}

		List<DictVO> list = dictService.searchData("性别", "XB", "性别", null, null);
		if (list != null && list.size() > 0) {
			DictVO dv = list.get(0);
			dv.setCodeVal("男");
			dictService.updateData(dv, "测试人");
		}
		List<DictVO> list2 = dictService.searchData("性别", "XB", null, null, null);
		for (int i = 0; i < list2.size(); i++) {
			System.out.println("更新后：" + list2.get(i).getCodeVal());
		}
		System.out.println("----------测试更新数据字典<结束>---------");
	}

	/**
	 * <b>案例:</b> deleteData(String codeCat,String codeKey,String codeVal,String sysInd, String lang) 删除数据字典 <br/>
	 * [前提条件]<br/>
	 * [输入参数]"类别","键","值","系统标识","语言"<br/>
	 * [预期输出]从数据库中删除满足条件的数据字典信息<br/>
	 * [预期异常]<br/>
	 */
	@Test
	public void testDelData() {
		// dictService.deleteData(null, "性别", "XB", null, null, null);
	}

}
