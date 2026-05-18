package com.back.domain.wiseSaying.wiseSaying.controller;

import com.back.domain.wiseSaying.wiseSaying.service.WiseSayingService;
import com.back.domain.wiseSaying.wiseSaying.entity.WiseSaying;
import com.back.standard.util.service.MarkdownService;
import lombok.RequiredArgsConstructor;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
//@RequestMapping("wiseSayings")
@RequiredArgsConstructor
public class WiseSayingController {
	private final WiseSayingService wiseSayingService;
	private final MarkdownService markdownService;

	@GetMapping("/wiseSayings/write")
	@ResponseBody
	public String write(
			@RequestParam(defaultValue = "내용")
			String content,
			@RequestParam(defaultValue = "작가")
			String author
	) {
//		if (content == null || content.isBlank()) {
		if (content.isBlank()) { //defaultvalue를 설정해서 null의 조건 삭제
			throw new IllegalArgumentException("Content cannot be null or blank");
		}

		if (author.isBlank()) {
			throw new IllegalArgumentException("author cannot be null or blank");
		}

		WiseSaying wiseSaying = wiseSayingService.write(content, author);

		return "%d번 명언이 생성되었습니다.".formatted(wiseSaying.getId());
	}

	@GetMapping("/wiseSayings")
	@ResponseBody
	public String list() {
		return "<h1>명언 목록</h1>\n"
				+
				"<ul>"
				+
				wiseSayingService.findAll()
						.stream()
						.map(wiseSaying -> "<li>%d / %s / %s</li>".formatted(wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent()))
						.collect(Collectors.joining("\n"))
				+
				"</ul>";
	}

	@GetMapping("/wiseSayings/{id}/delete")
	@ResponseBody
	public String delete(@PathVariable int id) {
		WiseSaying wiseSaying = wiseSayingService.findById(id)
				.orElseThrow(
						() -> new IllegalArgumentException("%d번 명언은 존재하지 않습니다.".formatted(id))
				);

		wiseSayingService.delete(wiseSaying);

		return "%d번 명언이 삭제되었습니다.".formatted(id);
	}

	@GetMapping("wiseSayings/{id}/modify")
	@ResponseBody
	public String modify(
			@PathVariable int id,
			@RequestParam(defaultValue = "") String content,
			@RequestParam(defaultValue = "") String author
	) {
		if (content.isBlank()) {
			throw new IllegalArgumentException("Content cannot be null or blank");
		}

		if (author.isBlank()) {
			throw new IllegalArgumentException("Author cannot be null or blank");
		}

		WiseSaying wiseSaying = wiseSayingService.findById(id).orElseThrow(() ->
				new IllegalArgumentException("%d번 명언은 존재하지 않습니다.".formatted(id))
		);
		wiseSayingService.modify(wiseSaying, content, author);
		return "%d번 명언이 수정되었습니다.".formatted(id);
	}

	@GetMapping("wiseSayings/{id}")
	@ResponseBody
	public String detail(
			@PathVariable int id
	) {
		WiseSaying wiseSaying = wiseSayingService.findById(id).get();
		String html = markdownService.toHtml(wiseSaying.getContent());
		return """
					<h1>명언 본문</h1>
					
					<div>번호 : %d</div>
					<div>작가 : %s</div>
					<div>%s</div>
				""".formatted(wiseSaying.getId(), wiseSaying.getAuthor(), html);
	}

//	private Optional<WiseSaying> findById(int id) {
//		return wiseSayings
//				.stream()
//				.filter(wiseSaying -> wiseSaying.getId() == id)
//				.findFirst();
//	}
}
