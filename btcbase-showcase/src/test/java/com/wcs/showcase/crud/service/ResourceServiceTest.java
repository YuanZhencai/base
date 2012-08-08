package com.wcs.showcase.crud.service;

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

import com.wcs.base.security.model.*;
import com.wcs.base.security.service.ResourceService;
import com.wcs.base.service.StatelessEntityService;

/**
 * <b>目标:</b> com.wcs.showcase.crud.service.PersonServerTest <br/> 
 * <b>维护:</b> <a href="mailto:liaowei@wcs-global.com">廖伟</a><br/>
 * <b>版本:</b> 1.0<br/>
 * Copyright © 2010-2012 Wilmar Consultancy Services
 */
@RunWith(Arquillian.class)
public class ResourceServiceTest{
	@Deployment
	public static Archive<?> createDeployment() {
		final MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class)
				.loadMetadataFromPom("pom.xml").goOffline();
		return ShrinkWrap.create(WebArchive.class, "test.war")
			.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
			.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
	
			.addPackages(true, "com/wcs")

			.addAsLibraries(resolver.configureFrom("D:\\dev\\build\\maven\\conf\\settings.xml")
                .artifact("org.primefaces:primefaces:3.0.1")
                .artifact("rapid:xsqlbuilder:1.0.4")
                .artifact("org.apache.commons:commons-lang3:3.1")
				.artifact("commons-collections:commons-collections:3.2.1")
				.artifact("commons-codec:commons-codec:1.5")
				.artifact("commons-io:commons-io:1.4")
				.artifact("commons-beanutils:commons-beanutils:1.8.0")
				.artifact("commons-configuration:commons-configuration:1.7")
				.artifact("com.google.guava:guava:r09")
				.artifact("ch.qos.cal10n:cal10n-api:0.7.4")
				.artifact("commons-fileupload:commons-fileupload:1.2.2")
				.artifact("org.apache.poi:poi:3.6")
				.artifact("org.apache.shiro:shiro-core:1.2.0")
				//.artifact("org.apache.shiro:shiro-faces:2.0-SNAPSHOT")
				.artifact("org.slf4j:slf4j-api:1.6.1")
				.artifact("org.slf4j:slf4j-ext:1.6.1")
				.artifact("org.slf4j:slf4j-log4j12:1.6.1")
				.artifact("log4j:log4j:1.2.16")
				//.artifact("net.sf.jasperreports:jasperreports")
                .resolveAsFiles());
}

	@Inject
	private ResourceService resourceService;
	
    /**
     * <b>案例:</b> search() 查询人员信息 <br/> 
     * [前提条件]<br/>
     * [输入参数]<br/> 
     * [预期输出]人员信息列表<br/>
     * [预期异常]<br/>
     */
	@Test
	public void testSearch(){
		List<Resource> list = resourceService.findAllSysResource();
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getName());
		}
	}
	

}
