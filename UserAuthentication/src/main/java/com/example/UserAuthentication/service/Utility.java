package com.example.UserAuthentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.UserAuthentication.entity.UserEntity;

public class Utility {
	
	
	public static String convertObjectToJson(Object user)  {
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			 return objectMapper.writeValueAsString(user);	
		} catch (JsonProcessingException e) {
			System.out.println("JSONProcessingException Occured");
			return "";
		}
	}
}
