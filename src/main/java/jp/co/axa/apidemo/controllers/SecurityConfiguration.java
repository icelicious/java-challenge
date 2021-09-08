package jp.co.axa.apidemo.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @description: SecurityConfiguration
 * @author: Li
 * @version: v1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http	.authorizeRequests()
				//USER can only use GET APIs
            	.antMatchers(HttpMethod.GET,"/api/v1/**").hasRole("USER")
            	//ADMIN can fetch all URIs
            	.antMatchers("/**").hasRole("ADMIN")
            	.anyRequest().authenticated()
            	.and()
            	.formLogin().and()
            	.httpBasic();
		//ignore csrf,need to check csrf token logic on prod
		http.csrf().ignoringAntMatchers("/h2-console/**").ignoringAntMatchers("/api/v1/**");
		http.headers().frameOptions().disable();
	}
  
   @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	   //for demo code,create two accounts
	   auth
       .inMemoryAuthentication()
       .withUser("admin").password("{noop}admin").roles("ADMIN", "USER") 
       .and()
       .withUser("spring").password("{noop}123456").roles("USER");
    }
}

