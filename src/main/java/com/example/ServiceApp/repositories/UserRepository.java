package com.example.ServiceApp.repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ServiceApp.models.User;

import io.r2dbc.spi.Option;

public interface UserRepository extends JpaRepository<User, Long> {
	Option<User> findByEmail(String email);
	boolean existByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE u.isActive = true")
	List<User> findAllActiveUser();
}
