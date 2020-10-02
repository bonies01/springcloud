package com.hy.auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

/**
 * 若干个WebSecurityConfigurerAdapter作用于一个WebSecurity生成一个最终使用的web安全过滤器
 * @author Administrator
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//Spring Security默认是禁用注解的，要想开启注解， 需要在继承
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 使用自定义登入身份认证组件
		// auth.authenticationProvider(new
		// JwtAuthenticationProvider(userDetailsService));
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 禁用 csrf, 由于使用的是JWT,我们这里不需要csrf
		http.cors().and().csrf().disable().authorizeRequests()
				// 跨域预检请求
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				// 登录URL
				.antMatchers("/login").permitAll()
				.antMatchers("/login.html").permitAll()
			    //允许所有用户访问"/"和"/index.html"
				//.antMatchers("/", "/index.html").hasAnyRole("roler_c1")
				// swagger
				.antMatchers("/swagger**/**").permitAll()
				.antMatchers("/webjars/**").permitAll()
				.antMatchers("/v2/**").permitAll()
				// 其他所有请求需要身份认证
				.anyRequest().authenticated()
				 .and()
	             .formLogin()
				.loginPage("/login.html")
				//  登录页
				.failureUrl("/login-error.html").permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/index.html");
		// 退出登录处理器
		//http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
		// 开启登录认证流程过滤器
		// http.addFilterBefore(new JwtLoginFilter(authenticationManager()),
		// UsernamePasswordAuthenticationFilter.class);
		// 访问控制时登录状态检查过滤器
		// http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()),
		// UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(new MyFilter(),UsernamePasswordAuthenticationFilter.class);
	}

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**");
    }
	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public static void main(String[] args) {
		System.err.println(new BCryptPasswordEncoder().encode("123456"));
	}
}
