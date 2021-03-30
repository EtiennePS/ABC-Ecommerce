package com.abcenterprise.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abcenterprise.ecommerce.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	User findByEmail(String email);
}
