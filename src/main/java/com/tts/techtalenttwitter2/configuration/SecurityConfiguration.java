package com.tts.techtalenttwitter2.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//configuring spring security
	//web security config adapter is a class provided by spring security 
	//by default: puts Spring Security in a lockdown config
	// by overrideing certain methods, can confige Spring Security differently
	
	//from application properties: links BD search to this config
	@Value ("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value ("${spring.queries.roles-query}")
	private String rolesQuery;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication() //want to use DB based auth
		//returns a builder object we can call methods on
		
			.usersByUsernameQuery(usersQuery)
			.authoritiesByUsernameQuery(rolesQuery)
			.dataSource(dataSource)
	        .passwordEncoder(bCryptPasswordEncoder);
		}
	
	@Override
	protected void configure(HttpSecurity http)throws Exception{
		http
			.authorizeRequests() //configures what weburls we have access to without login
				.antMatchers("/console/**").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/signup").permitAll()
				.antMatchers("custom.css").permitAll()
				.antMatchers().hasAuthority("USER").anyRequest().authenticated()
		.and()
			.formLogin() //this tells SpringSecurity that we will login using webpage with form
				.loginPage("/login")
				.failureUrl("/login?error=true")
				.defaultSuccessUrl("/tweets", true)
				.usernameParameter("username")
				.passwordParameter("password")
		.and() //conditions to logout on
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login")
		.and()
			.exceptionHandling()
		.and()
			.csrf().disable()
			.headers().frameOptions().disable();
	}
	
	@Override //configures what FILES can be accessed
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/resources/**", "/static/**", "/css/**", "/js/**",
				     "/images/**");
	}
	
}
