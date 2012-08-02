package com.wcs.common.service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.wcs.common.controller.vo.DictVO;
import com.wcs.common.model.Dict;

/**
 * <b>目标:</b> com.wcs.common.service.DictServiceTest <br/> 
 * <b>维护:</b> <a href="mailto:liaowei@wcs-global.com">廖伟</a><br/>
 * <b>版本:</b> 1.0<br/>
 * Copyright © 2010-2012 Wilmar Consultancy Services
 */
@RunWith(Arquillian.class)
public class DictServiceTest {
	
	@Deployment
	public static Archive<?> createDeployment() {
		final MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class)
				.loadMetadataFromPom("pom.xml").goOffline();
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackages(true, "com/wcs")

				.addAsLibraries(resolver.artifact("javax.ws.rs:jsr311-api").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.eclipse.persistence:javax.persistence").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.codehaus.jackson:jackson-core-lgpl").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.codehaus.jackson:jackson-mapper-lgpl").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.primefaces:primefaces").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.apache.openjpa:openjpa-all").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.apache.activemq:activemq-ra").resolveAsFiles())
				.addAsLibraries(resolver.artifact("com.wcs.cas:casclient").resolveAsFiles())
				.addAsLibraries(resolver.artifact("javax.faces:jsf-api").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.slf4j:slf4j-api").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.apache.shiro:shiro-core").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.apache.shiro:shiro-web").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.apache.shiro:shiro-ehcache").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.apache.shiro:shiro-faces").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.slf4j:slf4j-log4j12").resolveAsFiles())
				.addAsLibraries(resolver.artifact("commons-fileupload:commons-fileupload").resolveAsFiles())
				.addAsLibraries(resolver.artifact("commons-io:commons-io").resolveAsFiles())
				.addAsLibraries(resolver.artifact("commons-beanutils:commons-beanutils").resolveAsFiles())
				.addAsLibraries(resolver.artifact("rome:rome").resolveAsFiles())
				.addAsLibraries(resolver.artifact("jdom:jdom").resolveAsFiles())
				.addAsLibraries(resolver.artifact("net.sf.jasperreports:jasperreports").resolveAsFiles())
				.addAsLibraries(resolver.artifact("joda-time:joda-time").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.jboss.shrinkwrap.resolver:shrinkwrap-resolver-impl-maven").resolveAsFiles())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
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
	public void testInsertData(){
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
	public void testSearchData(){
		System.out.println("----------测试查询数据字典<开始>---------");
		List<DictVO> list = dictService.searchData(null, null, null, null, null);
		for(int i=0;i<list.size();i++){
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
	public void testUpdateData(){
		System.out.println("----------测试更新数据字典<开始>---------");
		
		List<DictVO> list1 = dictService.searchData("性别", "XB", null, null, null);
		for(int i=0;i<list1.size();i++){
			System.out.println("更新前：" + list1.get(i).getCodeVal());
		}
		
		List<DictVO> list = dictService.searchData("性别", "XB", "性别", null, null);
		if(list != null && list.size() > 0){
			DictVO dv = list.get(0);
			dv.setCodeVal("男");
			dictService.updateData(dv, "测试人");
		}
		List<DictVO> list2 = dictService.searchData("性别", "XB", null, null, null);
		for(int i=0;i<list2.size();i++){
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
	public void testDelData(){
		//dictService.deleteData(null, "性别", "XB", null, null, null);
	}

}
