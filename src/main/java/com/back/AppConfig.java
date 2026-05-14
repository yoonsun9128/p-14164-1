package com.back;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	/*
	* 간단한 경우에는 @Service와 같은 자동으로 생성하는걸 이용하는게 더 간편하다
	* */
//	@Bean
//	PersonService personService() {
//		System.out.println("personService 빈 생성됨!");
//		return new PersonService();
//	}
//	@Bean
//	PersonRepository personRepository() {
//		return new PersonRepository(1);
//	}
//
//	@Bean
//	PersonRepository personRepositoryV2() {
//		return new PersonRepository(2);
//	}
	@Bean
	int version() {
		return 55;
	}
}
