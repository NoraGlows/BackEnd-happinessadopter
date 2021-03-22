package com.ga.happinessadopter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
//override authentication
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	@Autowired
	JwtRequestFilter jwtRequestFilter;

	
//	override authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .csrf().disable()
		.authorizeRequests()
		.antMatchers("/pet/add", "/announcement/add").hasRole("owner") 
		.antMatchers("/pet/edit", "/announcement/edit").hasRole("owner")
		.antMatchers("/announcement/delete", "/announcement/edit").hasRole("owner")
		.antMatchers("/pet/delete").hasAnyRole("owner" , "adopter")
		.and()
		
		
		//we telling spring security don't generate session
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	//First apply filter then authenticate username and password
	http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	@Override
	@Bean // to solve authenticationManager error
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
