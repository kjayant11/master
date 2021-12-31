package com.example.pojo;
import java.util.List;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserRequestData {
	
	private int userId;
	// Case initiation
	@NotNull
	private String name;
	
	private int pinCode;
	
	private String surName;
	
	private String dob;
	
	private String joiningDate;
	
	private List<Responce> req;
	
}