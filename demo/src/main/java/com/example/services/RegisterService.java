package com.example.services;

import com.example.model.RegisterDTO;
import com.example.model.User;

public interface RegisterService {

	public User resgisterUser(RegisterDTO registerDTO);
	
	public User loginUser(User user, String token);
}
