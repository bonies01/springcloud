//package com.hy.auth;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
///**
// * 身份认证提供者
// * @author Administrator
// *
// */
//public class JwtAuthenticationProvider extends DaoAuthenticationProvider{
//	
//	public JwtAuthenticationProvider(UserDetailsService service) {
//		setUserDetailsService(service);
//		setPasswordEncoder(new BCryptPasswordEncoder());
//	}
//	
//	@Override
//	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//		//登入认证逻辑
//		return super.authenticate(authentication);
//	}
//	
//	@Override
//	protected void additionalAuthenticationChecks(UserDetails userDetails,
//			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
//		//密码验证
//		
//		super.additionalAuthenticationChecks(userDetails, authentication);
//	}
//}
