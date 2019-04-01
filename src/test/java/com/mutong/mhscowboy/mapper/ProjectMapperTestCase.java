package com.mutong.mhscowboy.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mutong.mhscowboy.entity.Project;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectMapperTestCase {

	@Autowired
	public ProjectMapper mapper;
	
	@Test
	public void insert() {
		Project project = new Project();
		project.setPronum("mt-cl-0001");
		project.setApplicant("长隆");
		project.setName("广东长隆水上乐园");
		project.setTime("2018-05-05");
		project.setPerson("张三");
		Integer rows = mapper.insert(project);
		System.err.println(rows);
	}
	
	@Test
	public void findByPronum() {
		Project project = mapper.findByPronum("mt-cl-0002");		
		System.err.println(project);
	}
	
	@Test
	public void findAllProject() {
		List<Project> list = mapper.findAllProject();
		for(Project data:list) {
			System.err.println(data);
		}
	}
	
	@Test
	public void findByParameter() {
		List<Project> list = mapper.findByParameter("采购");
		for(Project data:list) {
			System.err.println(data);
		}
		System.err.println("end");
	}
	
	@Test
	public void updateByPid() {
		Project project = new Project();
		project.setPid(4);
		project.setPronum("mt-sz-glh0001");
		project.setApplicant("深圳观澜湖");
		project.setName("深圳观澜湖沙滩椅采购");
		project.setTime("2017-12-06");
		project.setPerson("张三");
		Integer rows = mapper.updateByPid(project);
		System.err.println(rows);
	}
	
	@Test
	public void findByPid() {
		Project project = mapper.findByPid(1);
		System.err.println(project);
	}
	
	@Test
	public void deleteByPid() {
		Integer rows = mapper.deleteByPid(5);
		System.err.println(rows);
	}
}






