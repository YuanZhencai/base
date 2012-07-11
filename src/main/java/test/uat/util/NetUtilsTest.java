package test.uat.util;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.wcs.common.util.NetUtils;

/**
 * <p>Project: BTC</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2012 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:hujianguang@wcs-global.com">胡建光</a>
 */
public class NetUtilsTest {
    @Test
    public void testGetURLData() throws Exception {

        String uri = "http://10.228.191.203:9085/rs/data/SAP.HR.O?";

        String returnStr = NetUtils.getURLData(uri);
        assert !StringUtils.isEmpty(returnStr);
    }

}
