package com.mutong.mhscowboy.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mutong.mhscowboy.entity.Materiel;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MaterielMapperTestCase {

	@Autowired
	public MaterielMapper mapper;
	
	@Test
	public void insert() {
		Materiel materiel = new Materiel();
		materiel.setMatnum("MTMATERIEL-0001");
		materiel.setName("MT-SSD465487-JIO");
		materiel.setNum(500);
		materiel.setUnit("个");
		Integer rows = mapper.insert(materiel );
		System.err.println(rows);
	}
	
	@Test
	public void findByMatnum() {
		String matnum = "MTMATERIEL-000";
		Materiel materiel = mapper.findByMatnum(matnum );
		System.err.println(materiel);
	}
	
	@Test
	public void findAll() {
		List<Materiel> list = mapper.findAll();
		for(Materiel data:list) {
			System.err.println(data);
		}
	}
	
	@Test
	public void findByMid() {
		Materiel materiel = mapper.findByMid(3);
		System.err.println(materiel);
	}
	
	@Test
	public void findByParameter() {
		List<Materiel> list = mapper.findByParameter("852");
		for(Materiel data:list) {
			System.err.println(data);
		}
	}
	
	@Test
	public void updateByMid() {
		Materiel materiel = new Materiel();
		materiel.setMid(6);
		materiel.setMatnum("MTMATERIEL-0011");
		materiel.setName("MT-SSD456227-JPP");
		materiel.setUnit("个");
		materiel.setNum(99);
		materiel.setModifiedUser("xjw");
		materiel.setModifiedTime(new Date());
		Integer rows = mapper.updateByMid(materiel );
		System.err.println(rows);
	}
}






