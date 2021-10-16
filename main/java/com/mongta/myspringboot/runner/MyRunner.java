package com.mongta.myspringboot.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.mongta.myspringboot.property.MongtaProperties;

@Component
public class Myrunner implements ApplicationRunner{ // main이 시작되고 application 사작 하자마자 run method 시작됨
	
	Logger logger = LoggerFactory.getLogger(Myrunner.class);
	@Value("${mongta.name}")
	private String name;
	
	@Value("${mongta.age}")
	private int age;
	
	@Autowired
	private MongtaProperties property;
	
	@Autowired
	private String hello;
	
	@Override
	public void run(ApplicationArguments args) throws Exception { // args: main의 args가 넘어옴
		System.out.println("Myrunner start"+ logger.getClass().getName());
		
		logger.debug("Hello Bean:" + hello);
		logger.debug("Program arg:" + args.containsOption("bar"));
		logger.debug("VM arg:" + args.containsOption("foo"));	
		logger.debug("Name:" + name);	
		logger.info("Age:" + age);	
		logger.info("Prpoerty getfullnME:" + property.getFullName());	
		logger.info("Prpoerty name:" + property.getName());
		
	}

}
