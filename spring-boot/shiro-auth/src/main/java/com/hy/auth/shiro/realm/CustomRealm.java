package com.hy.auth.shiro.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

import com.hy.pojo.Permission;
import com.hy.pojo.Roler;
import com.hy.pojo.User;

public class CustomRealm extends AuthorizingRealm{
	
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection collection) {
		//登入用户名
		String username =String.valueOf(collection.getPrimaryPrincipal());
		//根据用户名用数据库查询用户信息
		User user=new User();
		user.setUsername("user");
		user.setPassword("123456");
		Roler rolerDto=new Roler();
		rolerDto.setRoleCode("admin");
		List<Roler> rolerList=new ArrayList<>();
		rolerList.add(rolerDto);		
		//权限
		Permission permissionDto=new Permission();
		permissionDto.setCode("add");
		List<Permission> permissionList=new ArrayList<>();
		permissionList.add(permissionDto);
		rolerDto.setPermList(permissionList);
		user.setRolerList(rolerList);
		//添加角色和权限
		SimpleAuthorizationInfo author=new SimpleAuthorizationInfo();
		for(Roler roler:user.getRolerList()) {
			//添加角色
			author.addRole(roler.getRoleCode());
			//添加权限
			for(Permission permission:roler.getPermList()) {
				author.addStringPermission(permission.getCode());
			}
		}
		return author;
	}
	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//加这一步的目的是在Post请求的时候会先进认证，然后在到请求
		if(StringUtils.isEmpty(token.getPrincipal()))return null;
		//获得登入用户信息
		String username=String.valueOf(token.getPrincipal());
		//从数据库查询用户信息
		User user=new User();
		user.setUsername("user");
		user.setPassword("123456");
		if(StringUtils.isEmpty(user)) {
			return null; //这里返回后会报出对应异常
		}
		else {
			return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),getName());
						
		}

	}

}
