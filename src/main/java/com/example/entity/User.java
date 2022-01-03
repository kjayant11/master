package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user_master")
//@SequenceGenerator(name="my_seq",sequenceName="MY_SEQ", allocationSize=1)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="sur_name")
	private String surName;
	
	@Column(name="pin_code")
	private Integer pinCode;
	
	@Column(name="dob")
	private Date dob;
	
	@Column(name="is_deleted", columnDefinition = "boolean default false")
	private Boolean deleted;
	
	@Column(name="joining_date")
	private Date joiningDate;
	
}