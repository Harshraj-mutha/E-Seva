package com.auth.AuthService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.AuthService.entity.Users;

@Repository
public interface AuthRepository extends JpaRepository<Users, Integer> {
	
	Optional<Users> findByEmail(String email);

}
