package com.example.UserAuthentication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UserAuthentication.entity.UserEntity;
import com.example.UserAuthentication.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;

	@Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	public UserEntity createUser(String username,String password,String name,String role) {
		
			UserEntity newUser=new UserEntity();
			newUser.setUsername(username);
			newUser.setPassword(PasswordEncoder.encodePassword(password)); 
			newUser.setName(name);
			newUser.setRole(role);
			return userRepository.save(newUser);
		
	}
	
	public UserEntity getUserByUsername(String username) {

		UserEntity user=userRepository.findByUsername(username);
		return user;
	}

}
