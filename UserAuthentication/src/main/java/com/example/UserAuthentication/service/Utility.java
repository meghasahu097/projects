package com.example.UserAuthentication.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.UserAuthentication.entity.UserEntity;

public class Utility {
	
	
	public static String convertObjectToJson(UserEntity user)  {
		Map<String,Object> map=new HashMap<>();
		map.put("id", user.getId());
		map.put("username", user.getUsername());
		map.put("name", user.getName());
		map.put("role", user.getRole());
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			 return objectMapper.writeValueAsString(map);	
		} catch (JsonProcessingException e) {
			System.out.println("JSONProcessingException Occured");
			return "";
		}
	}
	
	public static AuthHeaderValidation authorizeToken(String authorizationHeader){
		String[] str=authorizationHeader.split(" ");
		AuthHeaderValidation authToken=new AuthHeaderValidation();
		if(str.length==2 && str[0].equals("Bearer")){
			if(JwtTokenProvider.validateToken(str[1])) {
				String token=str[1];
				String username=JwtTokenProvider.getUsernameFromToken(token);
				authToken.setUsername(username);
				authToken.setValid(true);
				authToken.setErrormessage("Token Authorized");
			}else {
				 authToken.setErrormessage("Unauthorized token");
				}
			}else {
				 authToken.setErrormessage("Invalid token");
				}
		return authToken;
	}
}
