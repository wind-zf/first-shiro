package com.wind.shiro.realm;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class MapperRealm implements Realm {
	
	private static Map<String,String> map;
	
	static {
		map = new HashMap<String, String>();
		map.put("jack", "777");
		map.put("wind", "999");
		map.put("lucy", "888");
	}

	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
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

	public String getName() {
		return "maprealm";
	}

	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

}
