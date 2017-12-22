package com.mytaxi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * @author deniz.ozen
 * enables oauth2 security
 * generates a BEARER TOKEN to be carried with request
 * GET: 
 */
@Configuration
@EnableResourceServer
@EnableAuthorizationServer
public class MyTaxiOAuth2Config extends AuthorizationServerConfigurerAdapter
{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("UserDetailsService")
    private UserDetailsService userDetailsService;


    @Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
		.inMemory()
		.withClient("all")
		.secret("secret")
		.accessTokenValiditySeconds(3 * 60) //vanishes in 3 minutes
		.scopes("read", "write").authorizedGrantTypes("password", "refresh_token")
		.resourceIds("resource");
	}


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception
    {
        endpoints
            .authenticationManager(this.authenticationManager).userDetailsService(userDetailsService);
    }
}
