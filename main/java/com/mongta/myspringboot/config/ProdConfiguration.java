package com.mongta.myspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
@Profile("prod")
@Configuration
public class ProdConfiguration {
	@Bean
	public String hello() {
		//new Stiring
		return "운영 mode";
	}

}
