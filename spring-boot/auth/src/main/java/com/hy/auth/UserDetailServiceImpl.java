package com.hy.auth;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.hy.pojo.Permission;
import com.hy.pojo.Roler;
import com.hy.pojo.User;

import io.jsonwebtoken.lang.Arrays;
/**
 * 用户登入认证信息查询
 * @author Administrator
 *
 */
@Service
public class UserDetailServiceImpl implements  UserDetailsService{

	@SuppressWarnings("unused")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//验证用户 
		//根据用户名从数据库查询用户
		User user=new User();
		user.setUsername("user");
		//123456
		user.setPassword("$2a$10$xDBoo9BHJgrp9izE7k3KAegg2zYB5w8hhTirPeWqnKXJ9WAotTiBC");
		List<Roler> rolerList=new ArrayList<>();
		Roler roler=new Roler();
		roler.setRoleCode("roler_c2");
		roler.setRoleName("roler_n1");
		//权限
		Permission per=new Permission();
		per.setCode("hello");
		roler.setPermList(java.util.Arrays.asList(per));
		rolerList.add(roler);
		user.setRolerList(rolerList);
		if (null==user)throw new UsernameNotFoundException("该用户不存在");
		//验证角色
		//List<GrantedAuthority> rolerCheckList=new ArrayList<>();
		//验证用户拥有的权限
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		       for (Roler role : user.getRolerList()) {
		    	   authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleCode()));
		             for (Permission permission : role.getPermList()) {
		    	   	//权限如果前缀是ROLE_，security就会认为这是个角色信息，而不是权限，例如ROLE_MENBER就是MENBER角色，CAN_SEND就是CAN_SEND权限
		                 authorities.add(new SimpleGrantedAuthority(permission.getCode()));
		             }
		         }
		       return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
		     }

}
