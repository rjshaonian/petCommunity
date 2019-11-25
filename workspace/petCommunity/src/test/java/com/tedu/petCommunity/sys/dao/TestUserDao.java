package com.tedu.petCommunity.sys.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tedu.petCommunity.sys.entity.PetcUserPO;
import com.tedu.petCommunity.sys.service.UserService;

@SpringBootTest
public class TestUserDao {

	@Autowired
	private PetcUserDao userDao;
	
	@Test
	public void test() {
		PetcUserPO data = new PetcUserPO();
		data.setValid(1);
		data.setNickname("dsa");
		data.setPassword("jkujlkjhdlkjsalkd");
		data.setUsername("hdksajdlksajl");
		data.setSalt("dsadsaujdsaklj");
		
		userDao.insertAll(data);
	}

	
}
