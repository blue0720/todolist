package com.example.todolist.app.presentation.controller;

import jakarta.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.todolist.app.domain.model.TodoDto;
import com.example.todolist.app.domain.service.TodoService;
import com.example.todolist.app.domain.service.UserService;
import com.example.todolist.app.presentation.form.TodoForm;

@Controller
@RequestMapping("/todo")
public class TodoController {

	private final TodoService todoService;
	private final UserService userService;

	public TodoController(TodoService todoService, UserService userService) {
		this.todoService = todoService;
		this.userService = userService;
	}

	@GetMapping("/list")
	public String list(@AuthenticationPrincipal UserDetails userDetails,
			Model model) {
		Long userId = userService.findByUsername(userDetails.getUsername())
				.orElseThrow().getUserId();
		model.addAttribute("todos", todoService.findByUserId(userId));
		model.addAttribute("todoForm", new TodoForm());
		return "todo/list";
	}

	@PostMapping("/create")
	public String create(@Valid TodoForm todoForm,
			BindingResult bindingResult,
			@AuthenticationPrincipal UserDetails userDetails, // ← これが抜けていた
			Model model) {
		if (bindingResult.hasErrors()) {
			Long userId = userService.findByUsername(userDetails.getUsername())
					.orElseThrow().getUserId();
			model.addAttribute("todos", todoService.findByUserId(userId));
			return "todo/list";
		}
		Long userId = userService.findByUsername(userDetails.getUsername())
				.orElseThrow().getUserId();
		TodoDto todo = new TodoDto(null, todoForm.getTitle(), false, userId);
		todoService.create(todo);
		return "redirect:/todo/list";
	}

	@PostMapping("/complete/{todoId}")
	public String complete(@PathVariable Long todoId) {
		todoService.complete(todoId);
		return "redirect:/todo/list";
	}

	@PostMapping("/delete/{todoId}")
	public String delete(@PathVariable Long todoId) {
		todoService.delete(todoId);
		return "redirect:/todo/list";
	}
}
