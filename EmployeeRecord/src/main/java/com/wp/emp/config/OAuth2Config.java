package com.wp.emp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;


@Configuration
@EnableAuthorizationServer
/*@EnableGlobalMethodSecurity(prePostEnabled = true)*/
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;
	
	@Bean
	public TokenStore tokenStore() {
	    return new InMemoryTokenStore();
	}
	
	@Value("${wp.oauth.tokenTimeout:360000}")
	private int expiration;
	
	
	@Value("${wp.oauth.client:wealthPark}")
	private String client;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Value("${wp.oauth.secret:wealthParkClient}")
	private String secret;

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer configurer) throws Exception {

		configurer.authenticationManager(authenticationManager);
		configurer.userDetailsService(this.userDetailsService);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		clients.inMemory().withClient(client).secret(passwordEncoder().encode(secret)).accessTokenValiditySeconds(expiration)
				.scopes("read", "write").authorizedGrantTypes("password", "refresh_token").resourceIds("resource");
	}

}
