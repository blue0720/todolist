package com.example.todolist.app.presentation.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm {
	@NotBlank(message = "ユーザー名を入力してください")
	@Size(max = 50, message = "ユーザー名は50文字以内で入力してください")
	private String username;

	@NotBlank(message = "パスワードを入力してください")
	@Size(min = 8, max = 100, message = "パスワードは8文字以上100文字以内で入力してください")
	private String password;

}
