/** * MathImplTest.java 
* Created on 2011-11-9 下午1:49:23 
*/

package com.wcs.demo.test;

import junit.framework.TestCase;

/** 
* <p>Project: btcbase</p> 
* <p>Title: MathImplTest.java</p> 
* <p>Description: </p> 
* <p>Copyright: Copyright 2005-2011.All rights reserved.</p> 
* <p>Company: wcs.com</p> 
* @author <a href="mailto:yujingu@wcs-global.com">Yu JinGu</a> 
*/

public class MathImplTest extends TestCase {
    public void testAdd() throws Exception {
        MyMath m = new MathImpl();
        assertEquals(5, m.add(2, 3));
    }

    public void testSubtract() throws Exception {
        MyMath m = new MathImpl();
        assertEquals(-1, m.subtract(2, 3));
    }

}
