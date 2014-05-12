package com.wcs.common.util;

import java.io.File;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;

/** 
* <p>Project: btcbase</p> 
* <p>Title: Deployments.java</p> 
* <p>Description: </p> 
* <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
* <p>All Rights Reserved.</p>
* @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a> 
*/
public class Deployments {
	private static final String WEBAPP_SRC = "src/main/webapp";
	private static final String RESOURCE_SRC = "src/main/resources";

	public static WebArchive createDeployment() {

		final MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml").goOffline();
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackages(true, "com/wcs")

		.addAsLibraries(resolver.artifact("javax.ws.rs:jsr311-api").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.eclipse.persistence:javax.persistence").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.codehaus.jackson:jackson-core-lgpl").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.codehaus.jackson:jackson-mapper-lgpl").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.primefaces:primefaces").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.apache.openjpa:openjpa-all").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.apache.activemq:activemq-ra").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.jasig.cas.client:cas-client-core").resolveAsFiles())
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
				.addAsLibraries(resolver.artifact("rome:rome").resolveAsFiles()).addAsLibraries(resolver.artifact("jdom:jdom").resolveAsFiles())
				.addAsLibraries(resolver.artifact("net.sf.jasperreports:jasperreports").resolveAsFiles())
				.addAsLibraries(resolver.artifact("joda-time:joda-time").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.jboss.shrinkwrap.resolver:shrinkwrap-resolver-impl-maven").resolveAsFiles())
				.addAsWebResource(new File(WEBAPP_SRC, "index.jsp"))
				.addAsResource(new File(RESOURCE_SRC,"default.properties"))
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
}
