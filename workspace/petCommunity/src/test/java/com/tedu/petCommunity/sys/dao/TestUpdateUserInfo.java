package com.tedu.petCommunity.sys.dao;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tedu.petCommunity.sys.entity.PetcUserPO;

@SpringBootTest
public class TestUpdateUserInfo {
	@Autowired
	private PetcUserDao petcUserDao;

	@Test
	public void updateUserInfo() {
		PetcUserPO userInfo = new PetcUserPO();
		userInfo.setEmail("@163.com");
		userInfo.setMobile("1111111");
		userInfo.setModifiedTime(new Date());
		userInfo.setModifiedUser(11);
		userInfo.setNickname("meme");
		userInfo.setUsername("dad");
		userInfo.setValid(0);
		userInfo.setPetType("cat");
		userInfo.setId(1);
		int rows = petcUserDao.updateUserInfo(userInfo);
		System.out.println("修改" + rows);
	}

	@Test
	public void findUserInfo() {
		PetcUserPO userInfo = petcUserDao.findUserInfo(1);
		System.out.println(userInfo);
	}

}
