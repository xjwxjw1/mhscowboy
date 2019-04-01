package com.mutong.mhscowboy.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mutong.mhscowboy.entity.Dept;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeptMapperTestCase {

	@Autowired
	public DeptMapper mapper;
	
	@Test
	public void findAllDept() {
		List<Dept> list = mapper.findAllDept();
		for(Dept data:list) {
			System.err.println(data);
		}
	}
	
	@Test
	public void findByDid() {
		Dept data = mapper.findByDid(9);
		System.err.println(data);
	}
}






