/** * CDIInjectionTestCase.java 
* Created on 2011-11-11 下午5:44:59 
*/

package com.wcs.demo.test;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/** 
* <p>Project: btcbase</p> 
* <p>Title: CDIInjectionTestCase.java</p> 
* <p>Description: </p> 
* <p>Copyright: Copyright All rights reserved.</p> 
* <p>Company: wcs.com</p> 
* @author <a href="mailto:yujingu@wcs-global.com">Yu JinGu</a> 
*/
@RunWith(Arquillian.class)
public class CDIInjectionTestCase {
    @Deployment
    public static JavaArchive createTestArchive() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar").addClasses(GreetingManager.class, GreetingManagerBean.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
    }

    @Inject
    GreetingManager greetingManager;

    @Inject
    BeanManager beanManager;

    @Test
    public void shouldBeAbleToInjectCDI() throws Exception {
        String userName = "Earthlings";
        Assert.assertNotNull("Should have the injected the CDI bean manager", beanManager);
        Assert.assertEquals("Hello " + userName, greetingManager.greet(userName));
    }
}
