package com.mutong.mhscowboy.service;




import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mutong.mhscowboy.entity.Project;
import com.mutong.mhscowboy.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServiceTestCase {

	@Autowired
	public IProjectService projectService;
	
	@Test
	public void insert() {
		try {
			Project project = new Project();
			project.setPronum("mt-cl-0001");
			project.setApplicant("长隆");
			project.setName("广东长隆水上乐园");
			project.setTime("2018-05-05");
			project.setPerson("张三");
			projectService.addNew(project,11,"xjw");
			System.err.println("ok");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}	
	}
	
	@Test
	public void getAllProject() {
		System.err.println(projectService.getAllProject());
	}
	
	@Test
	public void getByParameter() {
		try {
			String parameter = "a";
			List<Project> list = projectService.getByParameter(parameter );
			for(Project data :list) {
				System.err.println(data);
			}
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void updateProject() {
		try {
			Project project = new Project();
			project.setPronum("mt-sz-glh0001");
			project.setApplicant("深圳观澜湖");
			project.setName("深圳观澜湖沙滩椅采购");
			project.setTime("2018-05-05");
			project.setPerson("admin");
			Integer pid = 4;
			String username = "admin";
			projectService.updateProject(project, pid , username );
			System.err.println("ok");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void removeProject() {
		try {
			Integer pid = 5;
			Integer uid = 1;
			projectService.removeProject(pid, uid);
			System.err.println("ok");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
}






