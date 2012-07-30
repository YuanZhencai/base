package com.wcs.showcase.crud.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

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

import com.wcs.base.service.StatelessEntityService;
import com.wcs.showcase.crud.model.Person;

/**
 * <b>目标:</b> com.wcs.showcase.crud.service.PersonServerTest <br/> 
 * <b>维护:</b> <a href="mailto:liaowei@wcs-global.com">廖伟</a><br/>
 * <b>版本:</b> 1.0<br/>
 * Copyright © 2010-2012 Wilmar Consultancy Services
 */
@RunWith(Arquillian.class)
public class PersonServerTest{
	@Deployment
	public static Archive<?> createDeployment() {
		final MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class)
				.loadMetadataFromPom("pom.xml").goOffline();
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackages(true, "com/wcs")

				.addAsLibraries(resolver.artifact("org.primefaces:primefaces").resolveAsFiles())
				.addAsLibraries(resolver.artifact("rapid:xsqlbuilder").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.apache.commons:commons-lang3").resolveAsFiles())
				.addAsLibraries(resolver.artifact("commons-collections:commons-collections").resolveAsFiles())
				.addAsLibraries(resolver.artifact("commons-codec:commons-codec").resolveAsFiles())
				.addAsLibraries(resolver.artifact("commons-io:commons-io").resolveAsFiles())
				.addAsLibraries(resolver.artifact("commons-beanutils:commons-beanutils").resolveAsFiles())
				.addAsLibraries(resolver.artifact("commons-configuration:commons-configuration").resolveAsFiles())
				.addAsLibraries(resolver.artifact("com.google.guava:guava").resolveAsFiles())
				.addAsLibraries(resolver.artifact("ch.qos.cal10n:cal10n-api").resolveAsFiles())
				.addAsLibraries(resolver.artifact("commons-fileupload:commons-fileupload").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.apache.poi:poi").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.apache.shiro:shiro-core").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.apache.shiro:shiro-faces").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.slf4j:slf4j-api").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.slf4j:slf4j-ext").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.slf4j:slf4j-log4j12").resolveAsFiles())
				.addAsLibraries(resolver.artifact("log4j:log4j").resolveAsFiles())
				.addAsLibraries(resolver.artifact("net.sf.jasperreports:jasperreports").resolveAsFiles())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Inject
	private StatelessEntityService entityService;
	
	@Inject
	private PersonService personService;
	
    /**
     * <b>案例:</b> search() 查询人员信息 <br/> 
     * [前提条件]<br/>
     * [输入参数]<br/> 
     * [预期输出]人员信息列表<br/>
     * [预期异常]<br/>
     */
	@Test
	public void testSearch(){
		List<Person> list = personService.search();
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getName());
		}
	}
	
	/**
     * <b>案例:</b> create(Person p) 单个添加人员信息 <br/> 
     * [前提条件]<br/>
     * [输入参数]Person对象<br/> 
     * [预期输出]数据库新增一条人员信息<br/>
     * [预期异常]<br/>
     */
	@Test
	public void testAdd(){
		Person p = new Person();
		p.setName("李四");
		p.setAddress("测试地址");
		p.setBirthday(new Date());
		p.setEmail("123@163.com");
		p.setPhone("9090950");
		p.setDefunctInd(false);
		entityService.create(p);
	}
	
	/**
     * <b>案例:</b> create(Person p) 批量添加人员信息 <br/> 
     * [前提条件]<br/>
     * [输入参数]Person对象<br/> 
     * [预期输出]数据库新增100条人员信息<br/>
     * [预期异常]<br/>
     */
	@Test
	public void testBatchAdd(){
		for(int i=0;i<100;i++){
			Person p = new Person();
			p.setName("姓名"+i);
			p.setAddress("地址"+i);
			p.setBirthday(new Date());
			p.setEmail("123@163.com");
			p.setPhone("9090950");
			p.setDefunctInd(false);
			entityService.create(p);
		}
	}
	
	/**
     * <b>案例:</b> create(Person p) 批量修改人员信息 <br/> 
     * [前提条件]<br/>
     * [输入参数]Person对象<br/> 
     * [预期输出]修改Id小于20的人员信息<br/>
     * [预期异常]<br/>
     */
	@Test
	public void testUpdate(){
		int j=0;
		List<Person> list = personService.search();
		System.out.println("************总共： "+list.size()+" 条数据************");
		for(int i=0;i<list.size();i++){
			if(list.get(i).getId()<20){
				Person p = list.get(i);
				p.setDefunctInd(true);
				entityService.update(list.get(i));
				j++;
			}
		}
		System.out.println("************已修改："+j+" 条数据************");
		List<Person> list1 = personService.search();
		System.out.println("************剩余： "+list1.size()+" 条数据************");
	}
	
	/**
     * <b>案例:</b> delete(Long id) 删除一条指定数据 <br/> 
     * [前提条件]<br/>
     * [输入参数]Id=5<br/> 
     * [预期输出]删除Id=5的数据<br/>
     * [预期异常]<br/>
     */
	@Test
	public void testDelete(){
		personService.delete(5l);
	}
	

}
