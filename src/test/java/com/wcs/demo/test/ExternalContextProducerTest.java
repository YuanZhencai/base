/** * ExternalContextProducerTest.java 
* Created on 2011-11-9 下午2:45:56 
*/

package com.wcs.demo.test;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ByteArrayAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/** 
* <p>Project: btcbase</p> 
* <p>Title: ExternalContextProducerTest.java</p> 
* <p>Description: </p>  
* <p>Copyright: Copyright 2005-2011.All rights reserved.</p> 
* <p>Company: wcs.com</p> 
* @author <a href="mailto:yujingu@wcs-global.com">Yu JinGu</a> 
*/ 
@RunWith(Arquillian.class)
public class ExternalContextProducerTest {  
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar").addClasses(MyMath.class, MathImpl.class)
                .addAsManifestResource(new ByteArrayAsset("<beans/>".getBytes()), ArchivePaths.create("beans.xml"));
    }

    @Inject 
    MyMath m;

    @Test
    public void testAdd() throws Exception {  
        assertEquals(5, m.add(2, 3));
    }

    @Test
    public void testSubtract() throws Exception {
        assertEquals(-1, m.subtract(2, 3));
    }

}
