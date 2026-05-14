package com.back;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

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

//	@Bean
//	@Order(2)
//	public ApplicationRunner myApplicationRunner() {
//		return new MyApplicationRunner();
//	}
//
//	@Bean
//	@Order(2)
//	public ApplicationRunner myApplicationRunner2() {
//		return new MyApplicationRunner2();
//	}
	@Bean
	ApplicationRunner myApplicationRunner() {
		return new ApplicationRunner() {
			@Override
			public void run(ApplicationArguments args) throws Exception {
				System.out.println("myApplicationRunner is running");
			}
		};
	}

	@Bean
	ApplicationRunner myApplicationRunner2() {
		return args -> {
			work1();
			work2();
		};
	}

	private void work1() {
		System.out.println("work1");
	}

	private void work2() {
		System.out.println("work2");
	}
}
