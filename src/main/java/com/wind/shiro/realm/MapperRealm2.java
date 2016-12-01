package com.wind.shiro.realm;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MapperRealm2 extends AuthorizingRealm {
	private static Map<String,String> map;
	static {
		map = new HashMap<String, String>();
		map.put("lucy", "888");
		map.put("jack", "999");
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = token.getPrincipal().toString();
		String password = new String((char[])token.getCredentials());
		if(!map.containsKey(userName)) {
			throw new UnknownAccountException("用户名不存在");
		}
		if(!map.get(userName).equals(password)) {
			throw new IncorrectCredentialsException("用户名或密码不正确");
		}
		return new SimpleAuthenticationInfo(userName, password,getName());
	}

}
