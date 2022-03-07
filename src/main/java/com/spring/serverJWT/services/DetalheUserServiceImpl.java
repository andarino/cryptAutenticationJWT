package com.spring.serverJWT.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.spring.serverJWT.Repository.UserRepository;
import com.spring.serverJWT.data.DetalheUserData;
import com.spring.serverJWT.model.UserModel;

@Component
public class DetalheUserServiceImpl implements UserDetailsService {
	
	private final UserRepository repository;

	public DetalheUserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserModel> usuario = repository.findByEmail(username);
		
		if(usuario.isEmpty()) {
			throw new UsernameNotFoundException("email [" +username+"] nao encontrado!!!");
		}
	
		return new DetalheUserData(usuario);
		
	}

}
