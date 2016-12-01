package com.wind.shiro.shiro_first;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class FirstTest {
	
	@Test
	public void test01() {
		SecurityManager manager = (SecurityManager) new IniSecurityManagerFactory("classpath:shiro.ini").getInstance();
		SecurityUtils.setSecurityManager(manager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken upt = new UsernamePasswordToken("lucy", "888");
		try {
			subject.login(upt);
			System.out.println("登录成功");
			System.out.println(upt.getPrincipal().toString());
		} catch (UnknownAccountException e) {
			System.out.println("用户名不存在");
		} catch (IncorrectCredentialsException e) {
			System.out.println("用户名或密码不正确");
		}
		
	}

}
