package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.RegisterDTO;
import com.example.model.User;
import com.example.repository.UserRepository;

@Service
public class RegisterServiceImpl implements RegisterService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User resgisterUser(RegisterDTO registerDTO) {

		 
		 User user = userRepository.findByEmail(registerDTO.getEmail());

		User savedUser = null;
		if (user.getEmail().isEmpty()) {
			user = new User();
			user.setEmail(registerDTO.getEmail());
			user.setFullName(registerDTO.getFull_name());
			user.setPassword(registerDTO.getPassword());
			user.setUsername(registerDTO.getUsername());
			savedUser = userRepository.save(user);
		} else {
			return user;
		}

		return savedUser;
	}

	@Override
	public User loginUser(User user, String token) {
		User onj = user;
		User savedUser = null;

		User userobj = new User();

		userobj.set_id(onj.get_id());
		userobj.setEmail(onj.getEmail());
		userobj.setFullName(onj.getFullName());
		userobj.setPassword(onj.getPassword());
		userobj.setToken(token);
		savedUser = userRepository.save(userobj);

		return savedUser;
	}

}
