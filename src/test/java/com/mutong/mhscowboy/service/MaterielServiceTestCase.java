package com.mutong.mhscowboy.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mutong.mhscowboy.entity.Materiel;
import com.mutong.mhscowboy.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MaterielServiceTestCase {

	@Autowired
	public IMaterielService materielService;
	
	@Test
	public void insert() {
		try {
			Materiel materiel = new Materiel();
			materiel.setMatnum("MTMATERIEL-0002");
			materiel.setName("MT-SSD955487-JIO");
			materiel.setUnit("套");
			materiel.setNum(200);
			Integer uid = 1;
			materielService.add(materiel, uid,"admin");
			System.err.println("ok");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}	
	}
	
	@Test
	public void getAll() {
		List<Materiel> list = materielService.getAll();
		for(Materiel data:list) {
			System.err.println(data);
		}
	}
	
	@Test
	public void getByMid() {
		Materiel data = materielService.getByMid(3);
		System.err.println(data);
	}
	
	@Test
	public void getByParameter() {
		try {
			List<Materiel> list = materielService.getByParameter("..");
			for(Materiel data:list) {
				System.err.println(data);
			}
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void updateMateriel() {
		try {
			Materiel materiel = new Materiel();
			materiel.setMatnum("MTMATERIEL-0001");
			materiel.setName("MT-SSD456227-KKK");
			materiel.setUnit("套");
			materiel.setNum(99);
			materielService.updateMateriel(materiel,16,1,"admin");
			System.err.println("ok");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
}






