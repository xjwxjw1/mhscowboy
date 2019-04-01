package com.mutong.mhscowboy.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mutong.mhscowboy.entity.Need;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NeedMapperTestCase {

	@Autowired
	public NeedMapper mapper;
	
	@Test
	public void insert() {
		Need need = new Need();
		need.setMatnum("MTMATERIEL-0003");
		need.setName("MT-SSD469527-JPL");
		need.setPerson("赵六");
		need.setTime("2018-08-08");
		need.setDemand(20);
		need.setAuditing("未审核");
		need.setProId(1);
		Date now = new Date();
		need.setCreatedUser("zhaoliu");
		need.setCreatedTime(now);
		need.setModifiedUser("zhaoliu");
		need.setModifiedTime(now);
		Integer rows = mapper.insert(need );
		System.err.println(rows);
	}
	
	@Test
	public void findByMatnumAndProId() {
		String matnum = "MTMATERIEL-0001";
		Integer proId = 1;
		Need need = mapper.findByMatnumAndProId(matnum, proId);
		System.err.println(need);
	}
	
	@Test
	public void findAllByPid() {
		Integer pid = 3;
		List<Need> list = mapper.findAllByPid(pid );
		for(Need data:list) {
			System.err.println(data);
		}
	}
	
	@Test
	public void findByParameter() {
		List<Need> list = mapper.findByParameter("11",1);
		for(Need data:list) {
			System.err.println(data);
		}
	}
	
	@Test
	public void findByNid() {
		Integer nid = 7;
		Need need = mapper.findByNid(nid  );
		System.err.println(need);
	}
	
	@Test
	public void updateByNid() {
		Need need = new Need();
		need.setMatnum("MTMATERIEL-0033");
		need.setName("MT-SSD649527-JPL");
		need.setPerson("赵六");
		need.setTime("2018-07-07");
		need.setDemand(20);
		need.setNid(1);
		Date now = new Date();
		need.setModifiedUser("zhaoliu");
		need.setModifiedTime(now);
		Integer rows = mapper.updateByNid(need);
		System.err.println(rows);
	}
}






