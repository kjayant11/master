package com.example.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.pojo.Responce;
import com.example.pojo.UserRequestData;
import com.example.service.UserService;

import javassist.tools.rmi.ObjectNotFoundException;


@RestController
public class UserController {

	@Autowired
	UserService emailService;

	@PostMapping("/usercreate")
	public Responce insertUser(@Valid @RequestBody UserRequestData userRequestData, BindingResult bindingResult) throws Exception{
		if(bindingResult.hasErrors()) {
			throw new Exception("Invalid Request");
		}
		return emailService.insertUser(userRequestData);
	}
	
	@PutMapping("/userUpdate")
	public Responce userUpdate(@Valid @RequestBody UserRequestData userRequestData, BindingResult bindingResult) throws Exception{
		if(bindingResult.hasErrors()) {
			throw new Exception("Invalid Request");
		}
		return emailService.userUpdate(userRequestData);
	}

	@GetMapping("/searchUser")
	public ResponseEntity<List<User>>  searchUser(@RequestParam("name") String name, @RequestParam("surName") String surName, @RequestParam("pinCode") Integer pinCode) throws Exception {
		return emailService.searchUser(name, surName, pinCode);
	}
	
	@GetMapping("/sortUser")
	public ResponseEntity<List<User>>  sortUser() throws Exception {
		return emailService.sortUser();
	}
	
	@DeleteMapping("/deleteUser")
	public ResponseEntity<Responce>  deleteUser(@RequestParam("type") String type, @RequestParam("id") Integer id) throws Exception {
		return emailService.deleteUser(type, id);
	}
}


