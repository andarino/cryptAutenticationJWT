package com.spring.serverJWT.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.serverJWT.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
	public Optional<UserModel> findByEmail(String email); 
	public UserModel save(UserModel userModel);
}
