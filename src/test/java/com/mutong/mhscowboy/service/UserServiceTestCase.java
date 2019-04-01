package com.mutong.mhscowboy.service;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mutong.mhscowboy.entity.User;
import com.mutong.mhscowboy.ex.ServiceException;
import com.mutong.mhscowboy.vo.UserVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTestCase {

	@Autowired
	public IUserService userService;
	
	@Test
	public void insert() {
		try {
			User user = new User();
			user.setUsername("root");
			user.setPassword("1234");
			userService.reg(user);
			System.err.println("ok");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}	
	}
	
	@Test
	public void login() {
		try {
			User user = new User();
			String username = "admin";
			String password = "8888";
			user = userService.login(username,password);
			System.err.println(user);
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}	
	}
	
	@Test
	public void changePassword() {
		try {
			Integer uid = 1;
			String oldPassword = "888888";
			String newPassword = "666666";
			String username = "admin";
			userService.changePassword(uid, oldPassword, newPassword, username);
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}	
	}
	
	@Test
	public void findUser() {		
		List<UserVO> list = userService.getUser();
		for(UserVO data:list) {
			System.err.println(data);
		}		
	}
	
	@Test
	public void changeInfo() {
		try {
			User user = new User();
			user.setName("admin");
			user.setGender(0);
			user.setEmail("admin@163.com");
			user.setPhone("1358958741");
			user.setDeptno(5);
			Integer loginUid = 1;
			Integer uid = 1;
			String username = "admin";
			userService.changeInfo(user, loginUid , uid , username );
			System.err.println("ok");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}	
	}
	
	@Test
	public void getByParameter() {	
		try {
			String letter = "张";
			List<UserVO> list = userService.getByParameter(letter);
			for(UserVO data:list) {
				System.err.println(data);
			}	
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}		
	}

	@Test
	public void getByDeptno() {		
		Integer count = userService.getByDeptno(2);
		System.err.println(count);
	}
}






