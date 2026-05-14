package com.back;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;


public class MyApplicationRunner implements ApplicationRunner {
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("MyApplicationRunner is running");
	}
}
