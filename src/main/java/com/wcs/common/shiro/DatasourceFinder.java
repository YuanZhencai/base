/** * ShiroRealm.java 
* Created on 2011-11-28 上午9:44:12 
*/

package com.wcs.common.shiro;

import java.io.Serializable;

import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wcs.common.model.User;
import com.wcs.common.service.permissions.UserService;

/** 
* <p>Project: btcbase</p> 
* <p>Title: ShiroRealm.java</p> 
* <p>Description: </p> 
* <p>Copyright: Copyright All rights reserved.</p> 
* <p>Company: wcs.com</p> 
* @author <a href="mailto:yujingu@wcs-global.com">Yu JinGu</a> 
*/
@SuppressWarnings("serial")
//@Named
public class DatasourceFinder implements Serializable {   
    private static final Logger log = LoggerFactory.getLogger(DatasourceFinder.class);
    
    private DataSource dataSource;
    
    public DatasourceFinder() throws NamingException {
       log.info("DatasourceFinder => constructor().");
       this.dataSource = (DataSource) new InitialContext().lookup("java:/jdbc/btcbase");
       System.out.println(233);
    }
    
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
    

}
