package com.poly.dax.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.poly.dax.service.AccountService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	AccountService accountService;
//	@Autowired
//	BCryptPasswordEncoder pe;
	@Qualifier("AccountDetailsServiceImpl")
    @Autowired
    @Lazy
    private UserService userService;
//	@Autowired
//	private CustomerOAuth2UserService customerOAuth2UserService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		auth.userDetailsService(email -> {
//			try {
//				Account user = accountService.findByEmail(email);
//				
//				String password = passwordEncoder().encode(user.getPassword());
//				String[] roles = user.getAuthorities().stream()
//						.map(er -> er.getRole().getId())
//						.collect(Collectors.toList()).toArray(new String[0]);
//				System.out.println("EMAIL: "+email);
//				return User.withUsername(email).password(password).roles(roles).build();
//			}catch(NoSuchElementException e){
//				throw new UsernameNotFoundException(email + "not found!");
//			}
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
		
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/checkout","/admin/**","/oauth/**").authenticated()
//			.antMatchers("/admin/**").hasRole("admin")
//			.antMatchers("/rest/authorities").hasRole("DIRE")
			.anyRequest().permitAll();
		
		http.formLogin()
			.loginPage("/security/login/form")
			.loginProcessingUrl("/security/login")
			.defaultSuccessUrl("/security/login/success",false)
			.failureUrl("/security/login/error");
		
		http.rememberMe()
			.tokenValiditySeconds(86400);
			
		http.exceptionHandling()
			.accessDeniedPage("/security/unauthoried");
		
		http.logout()
			.logoutUrl("/security/logoff")
			.logoutSuccessUrl("/security/logoff/success");
		
//		http.oauth2Login()
//		.loginPage("/security/login/form")
//		.userInfoEndpoint()
//		.userService(customerOAuth2UserService);
	}
	@Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers(HttpMethod.OPTIONS,"/**");
//	}
}
