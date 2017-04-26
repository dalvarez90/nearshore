package com.banamex.nearshore.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

import com.banamex.nearshore.gateway.security.NearshoreUserDetailsService;

@EnableWebSecurity
public class GatewayConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private NearshoreUserDetailsService nearshoreUserDetailsService;
	
	@Bean
    public HttpSessionStrategy httpSessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.userDetailsService(nearshoreUserDetailsService);
		
		http
				.httpBasic()
			.and()
				.logout()
			.and()
				.authorizeRequests().anyRequest().authenticated()
			.and()
				.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}
	
}
