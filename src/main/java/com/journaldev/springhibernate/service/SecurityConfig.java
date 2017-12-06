package com.journaldev.springhibernate.service;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
		
	@Autowired
	DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

	  auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(
			"select username,password,enabled from users where username=?")
		.authoritiesByUsernameQuery(
			"select username,role from user_roles where username=?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		  http.csrf().disable();
		
		  http.authorizeRequests()
			.antMatchers("/person/**").hasAnyRole("admin")
			.antMatchers("/logout.xhtml").hasAnyRole("admin","user")
			.antMatchers("/login.xhtml").permitAll()
			.and()
			  .formLogin().loginPage("/login.xhtml").failureUrl("/error.xhtml")
			  .loginProcessingUrl("/login")
			  .usernameParameter("username").passwordParameter("password")
			  .defaultSuccessUrl("/person.xhtml")
			.and()
			  .logout().logoutSuccessUrl("/logout.xhtml")
			.and()
			  .exceptionHandling().accessDeniedPage("/error.xhtml")
			.and().csrf()
			  ;
	}
	
	

}
