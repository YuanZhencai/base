/** * ShiroRealm.java 
* Created on 2011-11-28 上午9:44:12 
*/

package com.wcs.base.security.realms;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.wcs.base.security.model.Permission;
import com.wcs.base.security.model.Role;
import com.wcs.base.security.model.User;
import com.wcs.base.security.service.LoginService2;

/** 
* <p>Project: btcbase</p> 
* <p>Title: ShiroRealm.java</p> 
* <p>Description: </p> 
* <p>Copyright: Copyright All rights reserved.</p> 
* <p>Company: wcs.com</p> 
* @author <a href="mailto:yujingu@wcs-global.com">Yu JinGu</a> 
*/
@SuppressWarnings("serial")
public class CustomAuthorizer extends AuthorizingRealm implements Serializable {
	private BeanManager beanManager;
	private LoginService2 loginService;

	public CustomAuthorizer() throws NamingException {
		this.beanManager = (BeanManager) new InitialContext().lookup("java:comp/BeanManager");
	}

	/**
	 * 认证回调函数, 登录时调用
	 */
	@Override
	public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		loginService = this.getBean(LoginService2.class);
		User user = loginService.findUser(token.getUsername());
		if (user != null) {
			return new SimpleAuthenticationInfo(user.getLoginName(), user.getPassword(), getName());
		} else {
			return null;
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String loginName = principals.fromRealm(getName()).iterator().next().toString();
		loginService = this.getBean(LoginService2.class);
		User user = loginService.findUser(loginName);
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			for (Role role : user.getRoleList()) {
				List<Permission> permissions = loginService.findPermissions(role);
				for (Permission permission : permissions) {
					info.addStringPermission(permission.getPermission().toString());
				}
			}
			return info;
		} else {
			return null;
		}
	}

	private <T> T getBean(Class<? extends T> clazz) {
		@SuppressWarnings("unchecked")
		Bean<T> bean = (Bean<T>) beanManager.getBeans(clazz).iterator().next();
		CreationalContext<T> ctx = beanManager.createCreationalContext(bean);
		@SuppressWarnings("unchecked")
		T obj = (T) beanManager.getReference(bean, clazz, ctx);
		return obj;
	}

}
