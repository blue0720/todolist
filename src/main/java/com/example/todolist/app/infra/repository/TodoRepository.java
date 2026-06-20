package com.example.todolist.app.infra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todolist.app.infra.entity.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity,Long> {
	List<TodoEntity> findByUser_UserId(Long userId);
}
