package com.mutong.mhscowboy.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mutong.mhscowboy.entity.Supplier;
import com.mutong.mhscowboy.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SupplierServiceTestCase {

	@Autowired
	public ISupplierService supplierService;
	
	@Test
	public void add() {
		try {
			Supplier supplier = new Supplier();
			supplier.setSupnum("S0004");
			supplier.setName("浙江华夏游乐");
			supplier.setContacts("联系人3");
			supplier.setAddress("浙江省温州市永嘉县");
			supplier.setPhone("154444444444");
			supplier.setFax("010-000002");
			Integer uid = 0;
			String username = "admin";
			supplierService.add(supplier, uid, username);
			System.err.println("ok");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void remove() {
		try {
			Integer sid = 20;
			Integer uid = 0;
			supplierService.remove(sid, uid);
			System.err.println("ok");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void updateSupplier() {
		try {
			Supplier supplier = new Supplier();
			supplier.setSupnum("S0002");
			supplier.setName("广东童年之家");
			supplier.setContacts("联系人2");
			supplier.setAddress("广东清远");
			supplier.setPhone("13245698745");
			supplier.setFax("010-555555");
			Integer sid = 2;
			Integer uid = 17;
			String username = "caigou";
			supplierService.updateSupplier(supplier, sid, uid, username);
			System.err.println("ok");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
}






