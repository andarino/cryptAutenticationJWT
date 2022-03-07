package com.spring.serverJWT.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.serverJWT.model.UserModel;

public class DetalheUserData implements UserDetails{
	
	private final Optional<UserModel> user;

	public DetalheUserData(Optional<UserModel> user) {
		// TODO Auto-generated constructor stub
		this.user = user;
	}

	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		
		return user.orElse(new UserModel()).getPassword(); //so pra evitar null pointer
	}

	@Override
	public String getUsername() {
		return user.orElse(new UserModel()).getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
