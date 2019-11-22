package com.tedu.petCommunity.sys.dao;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tedu.petCommunity.common.util.IPUtils;
import com.tedu.petCommunity.sys.entity.PetcLogPO;

/**
 * @author 阳昊 下午4:18:35
 */
@SpringBootTest
public class TestPetcLogDao {

	@Autowired
	PetcLogDao logDao;

	@Test
	public void insertTest() {
		PetcLogPO logPO = new PetcLogPO();
		logPO.setId(null);
		logPO.setUserId(0);
		logPO.setUsername("test");
		logPO.setOperation("test");
		logPO.setMethod("test");
		logPO.setParams("test");
		logPO.setTime(1000l);
		logPO.setIp(IPUtils.getIpAddr());
		logPO.setCreatedTime(new Date());

		logDao.insertObject(logPO);
	}
}
