package com.mutong.mhscowboy.service;




import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mutong.mhscowboy.entity.Need;
import com.mutong.mhscowboy.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NeedServiceTestCase {

	@Autowired
	public INeedService needService;
	
	@Test
	public void insert() {
		try {
			Need need = new Need();
			need.setMatnum("MTMATERIEL-0004");
			need.setName("MT-SSD852527-JPL");
			need.setPerson("赵六");
			need.setTime("2018-08-08");
			need.setDemand(30);
			need.setAuditing("未审核");
			needService.add(need, 1, 14, "zhaoliu");
			System.err.println("ok");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void getAllByPid() {
		Integer pid = 1;
		List<Need> list = needService.getAllByPid(pid );
		for(Need data:list) {
			System.err.println(data);
		}
	}
	
	@Test
	public void getByNid() {
		try {
			Integer nid = 7;
			Need data = needService.getByNid(nid );
			System.err.println(data);
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void updateNeed() {
		try {
			Need need = new Need();
			need.setMatnum("MTMATERIEL-0044");
			need.setName("MT-SSD469527-JPL");
			need.setPerson("赵六");
			need.setTime("2018-08-08");
			need.setDemand(20);
			Date now = new Date();
			need.setModifiedUser("zhaoliu");
			need.setModifiedTime(now);
			Integer nid = 2;
			Integer uid = 11;
			String username = "zhaoliu";
			needService.updateNeed(need, nid , uid , username);
			System.err.println("ok");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void remove() {
		try {
			Integer nid = 9;
			Integer uid = 11;
			String username = "xjw";
			needService.remove(nid, uid, username);
			System.err.println("ok");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
}






