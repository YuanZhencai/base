/*
 * <P>Project: cameServer</P>
 * <P>Description: </P>
 * <P>Copyright (c) 2012 Wilmar Consultancy Services</P>
 * <P>All Rights Reserved.</P>
 * @author <a href="mailto:yansong@wcs-global.com">$Author$</a>
 */
package com.wcs.base.camel;
import java.util.Map;

import org.apache.camel.Headers;
import org.apache.camel.RoutingSlip;

/**
 * Bean which implements the logic where the message should be routed by the Dynamic Router EIP.
 * <p/>
 * Notice the bean has been annotated with @DynamicRouter
 *
 * @version $Revision$
 */
public class TestDynamicRouterBean {

    /**
     * The method invoked by Dynamic Router EIP to compute where to go next.
     * <p/>
     * Notice this method has been annotated with @DynamicRouter which means Camel turns this method
     * invocation into a Dynamic Router EIP automatically.
     *
     * @param body     the message body
     * @param previous the previous endpoint, is <tt>null</tt> on the first invocation
     * @return endpoint uri where to go, or <tt>null</tt> to indicate no more
     */
    @RoutingSlip
    public String route(String body, @Headers Map headers) {
    	String strBean = (String) headers.get("service");
    	System.out.println("********************TestDynamicRouterBean:"+strBean+"********************");
        return strBean;
        
    }
}