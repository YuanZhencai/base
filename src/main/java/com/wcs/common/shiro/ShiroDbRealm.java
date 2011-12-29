/** * ShiroRealm.java 
* Created on 2011-11-28 上午9:44:12 
*/

package com.wcs.common.shiro;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Singleton;

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
@Singleton
public class ShiroDbRealm extends AuthorizingRealm implements Serializable {   
    private static final Logger log = LoggerFactory.getLogger(ShiroDbRealm.class);
    
    @Inject
    private UserService userService;
    
    public ShiroDbRealm() {
       log.info("CustomAuthorizer => constructor().");
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String loginName = (String) principals.fromRealm(getName()).iterator().next();
        User user = userService.findUniqueUser(loginName);
        if (user != null) {
           SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
          /* for (Role role : user.getRoleSet()) {
               for (Permission permission : role.getPermissionSet()) {
                   info.addObjectPermission((org.apache.shiro.authz.Permission) permission);
               }
           }*/
           
           return info;
        } else {
            return null;
        }
    }

    /**
     * 认证回调函数, 登录时调用
     */
    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = userService.findUniqueUser(token.getUsername());
        if (user != null) {
            return new SimpleAuthenticationInfo(user.getUserName(), user.getUserPass(), getName());
        } else {
            return null;
        }
    }

}
