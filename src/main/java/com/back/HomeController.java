package com.back;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class HomeController {
	// 스프링이 주입하는게 아닌 내가 직접 생산
	private final PersonService personService = new PersonService();

	@GetMapping("/")
	@ResponseBody
	public String main() {
		long count = personService.count();
		return "main // 사람 수 : %d 명".formatted(count);
	}
}
