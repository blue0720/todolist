package com.example.todolist.app.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
	private Long todoId;
	private String title;
	private boolean completed;
	private Long userId;
}
