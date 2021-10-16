package com.mongta.myspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/mypage/**")
			.authenticated()
			.antMatchers("/**") // 나머지 다른 요청에 대해서는 permit all 하겠다
			.permitAll()        // return type을 바군다.
			.and()           	// HttpSecurity로 return type을 바꾸어준다-> HttpSecuritydml의 method를 사용 할 수 있도록 한다
			.formLogin()
			.and()
			.httpBasic()
			.and()
			.logout() 			//logout configuration
			.logoutUrl("/app-logout")     // html에서 오청한 url로 지정
			.deleteCookies("JSESSIONID")   // JSESSIONID: http spec에 정해저 있음
			.logoutSuccessUrl("/");
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}