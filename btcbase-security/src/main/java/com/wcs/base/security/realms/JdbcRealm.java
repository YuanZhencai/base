package com.wcs.base.security.realms;

import java.io.Serializable;
import java.util.Arrays;
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

import com.google.common.collect.Lists;
import com.wcs.base.security.model.Role;
import com.wcs.base.security.model.RoleResource;
import com.wcs.base.security.model.User;
import com.wcs.base.security.service.LoginService;
import com.wcs.base.util.ContainerUtils;

/**
 * <p>Project: btcbase-security</p> 
 * <p>Title: </p> 
 * <p>Description: </p> 
 * <p>Copyright: Copyright 2011-2020.All rights reserved.</p> 
 * <p>Company: wcs.com</p> 
 * @author guanjianghuai
 */
@SuppressWarnings("serial")
public class JdbcRealm extends AuthorizingRealm implements Serializable {
	
	private LoginService loginService;

	/**
	 * 认证回调函数, 登录时调用
	 */
	@Override
	public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		// 获取界面传递过来的认证信息（用户名/密码）
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		// 校验认证信息的合法性
		loginService = ContainerUtils.getBean(LoginService.class);
		User user = loginService.findUser(token.getUsername()); //adAccount
		
		return user==null ? null : new SimpleAuthenticationInfo(token.getUsername(), "", getName()); //密码为空
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String adAccount = principals.fromRealm(getName()).iterator().next().toString();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		// 查询登录用户的授权资源列表
		loginService = ContainerUtils.getBean(LoginService.class);
		
		List<Role> roles = loginService.findRoles(adAccount);
		
		for (Role role : roles){
			info.addRole(role.getCode());    // 定义 Role check
			List<RoleResource> permissions = loginService.findPermissions(role.getId());
			for (RoleResource p : permissions){
				info.addStringPermission(p.getCode());  // 定义 Permission check
			}
		}
		
		return info;
	}

}
