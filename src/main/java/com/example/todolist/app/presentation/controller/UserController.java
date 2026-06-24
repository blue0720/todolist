package com.example.todolist.app.presentation.controller;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.todolist.app.domain.model.UserDto;
import com.example.todolist.app.domain.service.UserService;
import com.example.todolist.app.presentation.form.UserForm;

@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "user/register";

	}

	@PostMapping("/register")
	public String register(@Valid UserForm userForm,
			BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "user/register";
		}
		try {
			UserDto user = new UserDto(null, userForm.getUsername(), userForm.getPassword());
			userService.register(user);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "user/register";
		}
		return "redirect:/login";
	}
}
