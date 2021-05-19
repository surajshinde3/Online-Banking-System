package com.zensar.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.zensar.model.User;

public interface UserService extends UserDetailsService {

	User findByEmail(String email);

	User findByUsername(String username);

	User save(User userDto);

	boolean checkUserExists(String username, String email);

	boolean checkUsernameExists(String username);

	boolean checkEmailExists(String email);
}