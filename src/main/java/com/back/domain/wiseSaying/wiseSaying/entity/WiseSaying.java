package com.back.domain.wiseSaying.wiseSaying.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WiseSaying {
	private int id;
	private String content;
	private String author;

	public void modify(String content, String author) {
		this.content = content;
		this.author = author;
	}
}
