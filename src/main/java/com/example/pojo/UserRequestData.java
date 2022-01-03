package com.example.pojo;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class UserRequestData {
	
	private Integer userId;
	// Case initiation
	@NotNull
	@NotEmpty
	private String name;
	
	@NotNull
//	@Size(min = 6, max = 6)
	private Integer pinCode;
	
	@NotEmpty
	@NotNull
	private String surName;
	
	@NotNull
	private String dob;
	
	@NotNull
	private String joiningDate;
	
	private List<Responce> req;
	
}