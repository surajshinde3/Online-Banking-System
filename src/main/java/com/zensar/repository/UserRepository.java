package com.zensar.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zensar.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	User findByEmail(String email);

	User findByUsername(String username);

}
