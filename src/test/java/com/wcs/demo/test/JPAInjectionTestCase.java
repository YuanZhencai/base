/** * JPAInjectionTestCase.java 
* Created on 2011-11-11 下午5:54:35 
*/

package com.wcs.demo.test;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/** 
* <p>Project: btcbase</p> 
* <p>Title: JPAInjectionTestCase.java</p> 
* <p>Description: </p> 
* <p>Copyright: Copyright All rights reserved.</p> 
* <p>Company: wcs.com</p> 
* @author <a href="mailto:yujingu@wcs-global.com">Yu JinGu</a> 
*/

@RunWith(Arquillian.class)
public class JPAInjectionTestCase {
    @Deployment
    public static WebArchive createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(TestUser.class, UserRepository.class, UserRepositoryBean.class)
                .addAsManifestResource("META-INF/test-persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB
    private UserRepository userRepository;

    @Test
    public void testCanPersistUserObject() {
        Assert.assertEquals("yes", userRepository.searchUser());
    }
}
