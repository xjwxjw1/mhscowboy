package com.mutong.mhscowboy.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mutong.mhscowboy.entity.Supplier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SupplierMapperTestCase {

	@Autowired
	public SupplierMapper mapper;
	
	@Test
	public void insert() {
		Supplier supplier = new Supplier();
		supplier.setSupnum("S0001");
		supplier.setName("浙江永浪集团");
		supplier.setContacts("联系人1");
		supplier.setAddress("浙江省温州市永嘉县");
		supplier.setPhone("15222222222");
		supplier.setFax("010-000000");
		Date now = new Date();
		supplier.setCreatedUser("admin");
		supplier.setCreatedTime(now);
		supplier.setModifiedUser("admin");
		supplier.setModifiedTime(now);
		Integer rows = mapper.insert(supplier );
		System.err.println(rows);
	}
	
	@Test
	public void findByName() {
		Supplier supplier = mapper.findByName("浙江永浪集团s");
		System.err.println(supplier);
	}
	
	@Test
	public void findAll() {
		List<Supplier> list = mapper.findAll();
		for(Supplier data:list) {
			System.err.println(data);
		}
	}
	
	@Test
	public void findBySid() {
		Supplier date = mapper.findBySid(20);
		System.err.println(date);
	}
	
	@Test
	public void findByParameter() {
		String parameter = "浙江";
		List<Supplier> list = mapper.findByParameter(parameter );
		for(Supplier data:list) {
			System.err.println(data);
		}
	}
	
	@Test
	public void updateBySid() {
		Supplier supplier = new Supplier();
		supplier.setSid(1);
		supplier.setSupnum("S0001");
		supplier.setName("浙江永浪集团");
		supplier.setContacts("联系人1");
		supplier.setAddress("浙江温州永嘉");
		supplier.setPhone("13589874563");
		supplier.setFax("010-010101");
		Integer rows = mapper.updateBySid(supplier);
		System.err.println(rows);
	}
}






