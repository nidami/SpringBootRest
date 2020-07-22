package com.deew.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.deew.AppServer.authentication.AppAuthEntryPoint;
import com.deew.AppServer.authentication.JwtAuthFilter;
import com.deew.AppServer.authentication.MyPassEncoder;

@EnableWebSecurity
public class SecurityConfig{
	@Configuration
	public static class AppSecurityConfig extends WebSecurityConfigurerAdapter{

		@Autowired
		private UserDetailsService userDetailService;
		
		@Autowired
		private AppAuthEntryPoint appAuthEntryPoint;

		@Autowired
		private JwtAuthFilter authfilter;
		
	
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
		}
		
		@Bean
		public PasswordEncoder passwordEncoder() {
			return new MyPassEncoder();
		}
		
		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
	
			http.antMatcher("/api/**")
			.authorizeRequests().antMatchers("/api/**").authenticated()
			.and().exceptionHandling()
			.authenticationEntryPoint(appAuthEntryPoint).and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			http.addFilterBefore(authfilter, UsernamePasswordAuthenticationFilter.class);
		}
		
	}
	
	@Order(1)
    @Configuration
    public static class ActuatorConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
        	http.headers().frameOptions().disable();
			http.csrf().disable();
			http.antMatcher("/h2-console/**").authorizeRequests()
            .antMatchers("/h2-console/**").permitAll();
                  
        }
    }
}




