package com.hy.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author Administrator
 *
 *         认证配置
 */
@SuppressWarnings("deprecation")
@Configuration
@EnableAuthorizationServer
public class AuthenServiceConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private AuthenticationManager authenticationManager;


	@Autowired
	private ClientDetailsService clientDetailsService;
	
	@Autowired
	private TokenStore tokenStore;
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
		.authenticationManager(authenticationManager)//认证管理器
		.authorizationCodeServices(authorizationCodeServices())//授权码服务
		.tokenServices(tokenService())//令牌管理服务
		.allowedTokenEndpointRequestMethods(HttpMethod.POST);		
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
			security// 开启/oauth/token_key验证端口无权限访问
         .tokenKeyAccess("permitAll()")
         // 开启/oauth/check_token验证端口认证权限访问
         //.checkTokenAccess("isAuthenticated()");
		.checkTokenAccess("permitAll()")
         //允许表单认证    请求/oauth/token的，如果配置支持allowFormAuthenticationForClients的，且url中有client_id和client_secret的会走ClientCredentialsTokenEndpointFilter
         .allowFormAuthenticationForClients();
		
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.inMemory()
			.withClient("client1")
			.secret(new BCryptPasswordEncoder().encode("secret1"))
			.authorizedGrantTypes("authorization_code")//授权类型，授权码
			.scopes("all")
			.autoApprove(Boolean.FALSE)
			.redirectUris("/center/redirect");//重定向地址
	}
	@Bean
	public AuthorizationCodeServices authorizationCodeServices() {
		return  new InMemoryAuthorizationCodeServices();
		
	}
	
	@Bean
	public AuthorizationServerTokenServices tokenService() {
		DefaultTokenServices tokenService=new DefaultTokenServices();
		tokenService.setClientDetailsService(clientDetailsService);
		tokenService.setSupportRefreshToken(true);
		tokenService.setTokenStore(tokenStore);
		//令牌默认有效期2小时
		tokenService.setAccessTokenValiditySeconds(7200);
		//刷新令牌默认有效期3天
		tokenService.setRefreshTokenValiditySeconds(259200);
		return tokenService;	
	}
	
}
