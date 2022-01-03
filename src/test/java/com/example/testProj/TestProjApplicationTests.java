package com.example.testProj;

import java.text.ParseException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.example.entity.User;
import com.example.pojo.Responce;
import com.example.pojo.UserRequestData;
import com.example.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class TestProjApplicationTests {

	@Autowired
	UserService emailService;

	@Test
	public void insertUser() throws Exception {
		UserRequestData user = new UserRequestData();
		user.setName("hello");
		user.setDob("22-01-1999");
		user.setJoiningDate("20-02-2022");
		Responce testName = emailService.insertUser(user);
		Assert.assertNotNull("name should not be null", user.getName());
		Assert.assertEquals("save data sucessfully", testName.getStatus());
	}
	
	@Test
	public void updateUser() throws Exception {
		UserRequestData user = new UserRequestData();
		user.setUserId(1);
		user.setDob("22-01-1999");
		user.setJoiningDate("20-02-2022");
		Assert.assertNull(user.getUserId());
		Responce testName = emailService.userUpdate(user);
		Assert.assertNotNull("name should not be null", user.getName());
		Assert.assertEquals("save data sucessfully", testName.getStatus());
	}

	@Test
	public void searchUser() throws Exception {
		UserRequestData user = new UserRequestData();
		user.setName("hello");
		ResponseEntity<List<User>> list = emailService.searchUser(user.getName(), user.getSurName(), user.getPinCode());
		Assert.assertNotNull("User Searched Successfully", list.getBody());
	}
	
	@Test
	public void sortUser() throws Exception {
		ResponseEntity<List<User>> list = emailService.sortUser();
		Assert.assertNotNull("User Sort Successfully", list.getBody());
	}

	@Test
	public void deleteUser() throws Exception {
		UserRequestData user = new UserRequestData();
		user.setUserId(17);
		ResponseEntity<Responce> list = emailService.deleteUser("soft", user.getUserId());
		Assert.assertEquals("Success", list.getBody().getStatus());
	}
}
