package com.example.todolist.app.presentation.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoForm {
	@NotBlank(message = "タイトルを入力してください")
	@Size(max = 100, message = "タイトルは100文字以内で入力してください")
	private String title;
}
