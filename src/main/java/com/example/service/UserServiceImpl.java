package com.example.service;

import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.pojo.UserRequestData;
import com.example.pojo .Responce;
import com.example.pojo.TestPojo;
import com.example.repo.UserRepository;
import com.google.gson.Gson;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class UserServiceImpl implements UserService {
	
	private static Pattern DATE_PATTERN = Pattern.compile(
		      "^\\d{2}-\\d{2}-\\d{4}$");

	@Autowired 
	UserRepository userRepository ;

	private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());

	@Override
	public  Responce insertUser(UserRequestData request) throws Exception {
		User user = new User();
		BeanUtils.copyProperties(request, user);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		if(Optional.ofNullable(request.getDob()).isPresent()) {
			String tmp[] = request.getDob().split("-");
			if(tmp.length != 0) {
				if(tmp[0].equals("00") || tmp[1].equals("00") || tmp[2].equals("00")) {
					throw new Exception();
				}
			}
			if(new Date().after(format.parse(request.getDob()))) {
				if(DATE_PATTERN.matcher(request.getDob()).matches()) {
					user.setDob(format.parse(request.getDob()));
				}else {
					throw new Exception();
				}
			}else {
				throw new Exception();
			}
		}
		
		if(Optional.ofNullable(request.getJoiningDate()).isPresent()) {
			String tmp[] = request.getJoiningDate().split("-");
			if(tmp.length != 0) {
				if(tmp[0].equals("00") || tmp[1].equals("00") || tmp[2].equals("00")) {
					throw new Exception();
				}
			}
			if(format.parse(request.getDob()).after(format.parse(request.getJoiningDate()))) {
				if(DATE_PATTERN.matcher(request.getJoiningDate()).matches()) {
					user.setJoiningDate(format.parse(request.getJoiningDate()));
				}else {
					throw new Exception();
				}
			}else {
				throw new Exception();
			}
		}
		System.out.println("user++++++++++++++++++++++++++++++++++++++++++++++++++" + user.getName());
		if(user != null) {
			user.setDeleted(false);
			userRepository.save(user);
		}

		Responce res = new Responce() ;
		res.setStatus("save data sucessfully");
		return res ;
	}

	@Override
	public ResponseEntity<List<User>> searchUser(String name, String surName, Integer pinCode) throws Exception {
		List<User> list = null;
		if(!(Optional.ofNullable(name).isPresent() || Optional.ofNullable(surName).isPresent() || Optional.ofNullable(pinCode).isPresent())) {
			throw new Exception();
		}
		
		list =  userRepository.findByNameEqualsIgnoreCaseOrPinCodeEqualsOrSurNameEqualsIgnoreCase(name, pinCode, surName);
		if(!Optional.ofNullable(list).isPresent()) {
			throw new Exception();
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<User>> sortUser() throws Exception {
		List<User> list = null;
		try {
			list =  userRepository.sortByDobAndJoiningDate();
		}catch(Exception e) {
			throw new Exception();
		}
		if(!Optional.ofNullable(list).isPresent()) {
			throw new Exception();
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Responce> deleteUser(String type, Integer id) throws Exception {
		if(!(Optional.ofNullable(type).isPresent() && Optional.ofNullable(id).isPresent())) {
			throw new Exception();
		}
		
		if(type.equalsIgnoreCase("hard")) {
			userRepository.delete(id);
		}else if(type.equalsIgnoreCase("soft")) {
			User user = userRepository.findByIdAndDeleted(id, false);
			if(!Optional.ofNullable(user).isPresent()) {
				throw new ObjectNotFoundException("Not Found");
			}
			
			user.setDeleted(true);
			userRepository.save(user);
		}
		Responce res = new Responce();
		res.setStatus("Success");
		return new ResponseEntity<>(res,HttpStatus.OK);
	}

	@Override
	public Responce userUpdate(UserRequestData request) throws Exception {
		User user = null;
		if(request.getUserId() != null) {
			user = userRepository.findById(request.getUserId());
		}
		
		if(!Optional.ofNullable(user).isPresent()) {
			throw new ObjectNotFoundException("Data Not Found");
		}
		BeanUtils.copyProperties(request, user);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		if(Optional.ofNullable(request.getDob()).isPresent()) {
			String tmp[] = request.getDob().split("-");
			if(tmp.length != 0) {
				if(tmp[0].equals("00") || tmp[1].equals("00") || tmp[2].equals("00")) {
					throw new Exception();
				}
			}
			if(new Date().after(format.parse(request.getDob()))) {
				if(DATE_PATTERN.matcher(request.getDob()).matches()) {
					user.setDob(format.parse(request.getDob()));
				}else {
					throw new Exception();
				}
			}else {
				throw new Exception();
			}
		}
		
		if(Optional.ofNullable(request.getJoiningDate()).isPresent()) {
			String tmp[] = request.getJoiningDate().split("-");
			if(tmp.length != 0) {
				if(tmp[0].equals("00") || tmp[1].equals("00") || tmp[2].equals("00")) {
					throw new Exception();
				}
			}
			if(format.parse(request.getDob()).after(format.parse(request.getJoiningDate()))) {
				if(DATE_PATTERN.matcher(request.getJoiningDate()).matches()) {
					user.setJoiningDate(format.parse(request.getJoiningDate()));
				}else {
					throw new Exception();
				}
			}else {
				throw new Exception();
			}
		}
		System.out.println("user++++++++++++++++++++++++++++++++++++++++++++++++++" + user.getName());
		if(user != null) {
			userRepository.save(user) ;
		}

		Responce res = new Responce() ;
		res.setStatus("update data sucessfully");
		return res ;
	}
}
