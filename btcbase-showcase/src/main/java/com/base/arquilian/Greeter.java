/** * Greeter.java 
* Created on 2012-4-27 上午11:31:23 
*/

package com.base.arquilian;

import java.io.PrintStream;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* <p>Project: arquillian-tutorial</p> 
* <p>Title: Greeter.java</p> 
* <p>Description: </p> 
* <p>Copyright: Copyright 2012-2020.All rights reserved.</p> 
* <p>Company: wcs.com</p> 
* @author <a href="mailto:yujingu@wcs-global.com">Yu JinGu</a> 
*/
@Named
public class Greeter {
    public void greet(PrintStream to, String name) {
        to.println(createGreeting(name));
    }

    public String createGreeting(String name) {
        return "Hello, " + name + "!";
    }
}
