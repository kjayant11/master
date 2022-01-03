package com.example.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.entity.User;
import com.example.pojo.Responce;
import com.example.pojo.UserRequestData;

public interface UserService {
	
	public Responce insertUser(UserRequestData request ) throws ParseException, Exception;

	public ResponseEntity<List<User>> searchUser(String name, String surName, Integer pinCode) throws Exception;

	public ResponseEntity<Responce> deleteUser(String type, Integer id) throws Exception;

	public ResponseEntity<List<User>> sortUser() throws Exception;

	public Responce userUpdate(UserRequestData userRequestData) throws ParseException, Exception;
}
