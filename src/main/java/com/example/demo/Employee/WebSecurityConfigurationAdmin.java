package com.example.demo.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@Order(1000)
public class WebSecurityConfigurationAdmin extends WebSecurityConfigurerAdapter{
	
	private final EmployeeService employeeService;
	
	@Bean
	public SCryptPasswordEncoder getsCryptPasswordEncoder() {
		return new SCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests().antMatchers("/admin/**").hasAnyAuthority("ADMIN")
		.and().formLogin()
		.and().logout().clearAuthentication(true).logoutSuccessUrl("/")
			.deleteCookies("JSESSIONID").invalidateHttpSession(true)
		.and().csrf().disable();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(getsCryptPasswordEncoder());
		provider.setUserDetailsService(employeeService);
		return provider;
	}

}
