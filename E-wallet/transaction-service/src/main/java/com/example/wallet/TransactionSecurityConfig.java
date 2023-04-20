package com.example.wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class TransactionSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	TransactionService transactionService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(transactionService);
	
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.httpBasic()
		.and().csrf().disable()
		.authorizeHttpRequests()
		.antMatchers("/transact/**").hasAuthority("usr")
		.and().formLogin();
	}
}
