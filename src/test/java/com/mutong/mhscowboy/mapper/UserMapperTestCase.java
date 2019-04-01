package com.mutong.mhscowboy.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mutong.mhscowboy.entity.User;
import com.mutong.mhscowboy.vo.UserVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTestCase {

	@Autowired
	public UserMapper mapper;
	
	@Test
	public void insert() {
		User user = new User();
		user.setUsername("root");
		user.setPassword("1234");
		user.setName("张三");
		Integer rows = mapper.insertUser(user);
		System.err.println("rows=" + rows);
	}
	
	@Test
	public void findByUsername() {
		String username = "xjw";
		User user = mapper.findByUsername(username);
		System.err.println(user);
	}
	
	@Test
	public void updatePassword() {		
		Integer uid = 1;
		String newPassword = "6666";
		String modifiedUser = "admin";
		Date modifiedTime = new Date();
		Integer rows = mapper.updatePassword(uid, newPassword, modifiedUser, modifiedTime);
		System.err.println(rows);
	}
	
	@Test
	public void findUser() {		
		List<UserVO> list = mapper.findUser();
		for(UserVO data:list) {
			System.err.println(data);
		}		
	}
	
	@Test
	public void findByUid() {		
		Integer uid =1;
		User user = mapper.findByUid(uid );
		System.err.println(user);	
	}
	
	@Test
	public void updateInfo() {		
		User user = new User();
		user.setUid(1);
		user.setName("lll");
		user.setGender(0);
		user.setEmail("lll@163.com");
		user.setPhone("1358958741");
		user.setDeptno(7);
		Integer rows = mapper.updateInfo(user);	
		System.err.println(rows);
	}
	
	@Test
	public void delete() {		
		Integer uid = 1;
		Integer rows = mapper.delete(uid);
		System.err.println(rows);
	}
	
	@Test
	public void findByLetter() {
		String letter = "张";
		List<UserVO> list = mapper.findByLetter(letter);
		System.err.println(list.size());
		for(UserVO data:list) {
			System.err.println(data);
		}
		System.err.println("ok");
	}

	@Test
	public void findByDeptno() {		
		Integer count = mapper.findByDeptno(2);
		System.err.println(count);
	}
}






