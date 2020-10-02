package com.hy.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 安全认证
 * @author Administrator
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManagerBean();
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 禁用 csrf, 由于使用的是JWT,我们这里不需要csrf
		http.cors().and().csrf().disable().authorizeRequests()
				// 跨域预检请求
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				// 登录URL
				.antMatchers("/login").permitAll()
				.anyRequest().authenticated()
				.and()
	             .formLogin()
				.loginPage("/login.html")
				//  登录页
				.failureUrl("/login-error.html").permitAll()
               .and()
               .logout()
               .logoutSuccessUrl("/index.html");
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.err.println("内存密码验证---------------");
		auth.inMemoryAuthentication()
		.passwordEncoder(new PasswordEncoder() {			
			@Override
			public boolean matches(CharSequence charsequence, String s) {
				return s.equals(String.valueOf(charsequence));
			}			
			@Override
			public String encode(CharSequence charsequence) {
				return String.valueOf(charsequence);
			}
		})
		.withUser("user").password("123456");
	}

}
