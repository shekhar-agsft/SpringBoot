package com.example.security;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.example.model.User;

public final class JwtUserFactory {

	private JwtUserFactory() {
	}

	public static JwtUser create(User user) {
		return new JwtUser(user.get_id(), user.getEmail(), user.getFullName(), user.getPassword(),
				new ArrayList<GrantedAuthority>(), // mapToGrantedAuthorities(user.getAuthorities()),
				user.getUsername());

	}

	@SuppressWarnings("unused")
	private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> authorities) {
		return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getName()))
				.collect(Collectors.toList());
	}
}
