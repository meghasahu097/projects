package com.example.UserAuthentication.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserAuthentication.entity.UserEntity;
import com.example.UserAuthentication.service.AuthHeaderValidation;
import com.example.UserAuthentication.service.JwtTokenProvider;
import com.example.UserAuthentication.service.PasswordEncoder;
import com.example.UserAuthentication.service.UserService;
import com.example.UserAuthentication.service.Utility;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	public AuthController(UserService userService){
			this.userService=userService;
	}
	
	@PostMapping("/sign_up")
	public ResponseEntity<?> signUp(@RequestBody UserEntity user){
		if(userService.getUserByUsername(user.getUsername())!=null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Already Exists");
		}else {
			userService.createUser(user.getUsername(), user.getPassword(),user.getName(),user.getRole());
			return ResponseEntity.ok("User Created Successfully");
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserEntity u){

		UserEntity user = userService.getUserByUsername(u.getUsername());
		if(user==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Username");
		}
		else if(PasswordEncoder.verifyPassword(u.getPassword(),user.getPassword())) {
			System.out.print("User Login Successful");
			String jwt=JwtTokenProvider.generateToken(u.getUsername());
			return ResponseEntity.status(HttpStatus.OK).body(jwt);
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication Failed (Invalid Password)");
		}
    }
	
	@GetMapping(value="/lookup_user",produces="application/json")
	public ResponseEntity<?> lookupUser(@RequestHeader("Authorization") String authorizationHeader) {
		AuthHeaderValidation authToken=Utility.authorizeToken(authorizationHeader);
		
		if(authToken.isValid()) {
			UserEntity user=userService.getUserByUsername(authToken.getUsername());
			String response=Utility.convertObjectToJson(user);
							
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authToken.getErrormessage());
		}
		
	}
	
}
