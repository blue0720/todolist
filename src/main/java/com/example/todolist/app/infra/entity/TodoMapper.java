package com.example.todolist.app.infra.entity;

import com.example.todolist.app.domain.model.TodoDto;

public class TodoMapper {

	public static TodoDto toModel(TodoEntity entity) {
		return new TodoDto(
				entity.getTodoId(),
				entity.getTitle(),
				entity.isCompleted(),
				entity.getUser().getUserId());
	}

	public static TodoEntity toEntity(TodoDto model, UserEntity user) {
		TodoEntity entity = new TodoEntity();
		entity.setTodoId(model.getTodoId());
		entity.setTitle(model.getTitle());
		entity.setCompleted(model.isCompleted());
		entity.setUser(user);
		return entity;
	}
}
