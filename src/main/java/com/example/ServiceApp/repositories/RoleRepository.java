package com.example.ServiceApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ServiceApp.models.Role;

import io.r2dbc.spi.Option;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Option<Role> findByName(Role name);
}
