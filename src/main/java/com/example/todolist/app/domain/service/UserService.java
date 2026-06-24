package com.example.todolist.app.domain.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todolist.app.domain.model.UserDto;
import com.example.todolist.app.infra.entity.UserEntity;
import com.example.todolist.app.infra.entity.UserMapper;
import com.example.todolist.app.infra.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public UserDto register(UserDto user) {
		if (userRepository.findByUsername(user.getUsername()).isPresent()) {
			throw new IllegalStateException("このユーザー名は既に使われています");
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		UserEntity saved = userRepository.save(UserMapper.toEntity(user));
		return UserMapper.toModel(saved);
	}

	public Optional<UserDto> findByUsername(String username) {
		return userRepository.findByUsername(username)
				.map(UserMapper::toModel);
	}
}
