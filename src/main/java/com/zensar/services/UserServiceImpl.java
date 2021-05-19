package com.zensar.services;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zensar.model.Role;
import com.zensar.model.User;
import com.zensar.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountService service;

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
//				.orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthorities(user));
	}

	public User save(User registration) {
		User user = new User();
		user.setFirstName(registration.getFirstName());
		user.setLastName(registration.getLastName());
		user.setUsername(registration.getUsername());
		user.setCurrentCity(registration.getCurrentCity());
		user.setCurrentState(registration.getCurrentState());
		user.setCurrentCity(registration.getCurrentCity());
		user.setCurrentCountry(registration.getCurrentCountry());
		user.setCurrentPinCode(registration.getCurrentPinCode());
		user.setPermanentCity(registration.getPermanentCity());
		user.setPermanentState(registration.getPermanentState());
		user.setPermanentCountry(registration.getPermanentCountry());
		user.setPermanentPinCode(registration.getPermanentPinCode());
		user.setAadharNumber(registration.getAadharNumber());
		user.setPanNumber(registration.getPanNumber());
		user.setMobileNumber(registration.getMobileNumber());
		user.setEmail(registration.getEmail());
		user.setPassword(registration.getPassword());
		user.setRoles(Arrays.asList(new Role("ROLE_USER")));

		user.setSalaryAccount(service.createSalaryAccount());
		user.setSavingsAccount(service.createSavingsAccount());
		return userRepository.save(user);
	}

	public boolean checkUserExists(String username, String email) {
		if (checkUsernameExists(username) || checkEmailExists(username)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkUsernameExists(String username) {
		if (null != findByUsername(username)) {
			return true;
		}

		return false;
	}

	public boolean checkEmailExists(String email) {
		if (null != findByEmail(email)) {
			return true;
		}

		return false;
	}

	private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
		String[] userRoles = user.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
		return authorities;
	}
}
