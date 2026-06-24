package com.example.todolist.app.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todolist.app.domain.model.TodoDto;
import com.example.todolist.app.infra.entity.TodoEntity;
import com.example.todolist.app.infra.entity.TodoMapper;
import com.example.todolist.app.infra.entity.UserEntity;
import com.example.todolist.app.infra.repository.TodoRepository;
import com.example.todolist.app.infra.repository.UserRepository;

@Service
public class TodoService {

	private final TodoRepository todoRepository;
	private final UserRepository userRepository;

	public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
		this.todoRepository = todoRepository;
		this.userRepository = userRepository;
	}

	public TodoDto create(TodoDto todo) {
		UserEntity user = userRepository.getReferenceById(todo.getUserId());
		TodoEntity saved = todoRepository.save(TodoMapper.toEntity(todo, user));
		return TodoMapper.toModel(saved);
	}

	public List<TodoDto> findByUserId(Long userId) {
		return todoRepository.findByUser_UserId(userId)
				.stream()
				.map(TodoMapper::toModel)
				.toList();
	}

	public void complete(Long todoId) {
		TodoEntity entity = todoRepository.findById(todoId)
				.orElseThrow(() -> new IllegalArgumentException("指定されたToDoが見つかりません"));
		entity.setCompleted(true);
		todoRepository.save(entity);
	}

	public void delete(Long todoId) {
		todoRepository.deleteById(todoId);
	}
}
