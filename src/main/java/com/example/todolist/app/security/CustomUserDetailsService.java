package com.example.todolist.app.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.todolist.app.domain.model.UserDto;
import com.example.todolist.app.domain.service.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        UserDto userDto = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "ユーザーが見つかりません: " + username));
        return new CustomUserDetails(userDto);
    }
}