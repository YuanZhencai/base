/** * EjbInjectionTestCase.java 
* Created on 2011-11-11 下午5:30:58 
*/

package com.wcs.demo.test;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: EjbInjectionTestCase.java</p> 
 * <p>Description: </p> 
 * <p>Copyright: Copyright All rights reserved.</p> 
 * <p>Company: wcs.com</p> 
 * @author <a href="mailto:yujingu@wcs-global.com">Yu JinGu</a> 
 */
@RunWith(Arquillian.class)
public class EjbInjectionTestCase {
    @Deployment
    public static JavaArchive createTestArchive() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar").addClasses(GreetingManager.class, GreetingManagerBean.class);
    }

    @EJB
    private GreetingManager greetingManager;

    @Test
    public void shouldBeAbleToInjectEJB() throws Exception {
        String userName = "Earthlings";
        Assert.assertEquals("Hello " + userName, greetingManager.greet(userName));
    }
}
