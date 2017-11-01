package com.example.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.utility.AuthorityRepository;


@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityRepository authorityRepository;

	@Override
	public List<Role> getAuthorityList() {
		return authorityRepository.findAll();
	}

	@Override
	public Role getAuthorityByName(String roleName) {
		return authorityRepository.findByName(roleName);
	}
}
