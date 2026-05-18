package com.back;

import com.back.domain.member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import com.back.domain.member.member.entity.Member;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
	private final MemberService memberService;
	@Autowired
	@Lazy //순환 참조 방지
	private AppConfig self; //자기 자신을 Bean으로 주입
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
//	@Bean
//	ApplicationRunner myApplicationRunner() {
//		return new ApplicationRunner() {
//			@Override
//			public void run(ApplicationArguments args) throws Exception {
//				System.out.println("myApplicationRunner is running");
//			}
//		};
//	}

//
	@Bean
	ApplicationRunner makeSampleMemberDataApplicationRunner() {
		return args -> {
			self.work1();
			self.work2();
		};
	}

	@Transactional
	public void work1() {
		if (memberService.count() > 0) return;
		Member memberSystem = memberService.join("system", "1234", "시스템");
		Member memberAdmin = memberService.join("admin", "1234", "관리자");
		Member memberUser1 = memberService.join("user1", "1234", "유저1");
		Member memberUser2 = memberService.join("user2", "1234", "유저2");
		Member memberUser3 = memberService.join("user3", "1234", "유저3");
	}

	@Transactional
	public void work2() {
		Member memberUser2 = memberService.findByUsername("user2").get();

		memberUser2.setNickname("유저2 New");
	}
}
