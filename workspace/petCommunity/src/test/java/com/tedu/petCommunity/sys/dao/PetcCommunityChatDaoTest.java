/**
 * 
 */
package com.tedu.petCommunity.sys.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author VictorHe 2019年11月24日 下午8:45:05
 */
@SpringBootTest
public class PetcCommunityChatDaoTest {
	@Autowired
	PetcChatDao petcCommunityDao;

	@Test
	public void testGetRowCount() {
		System.out.println(petcCommunityDao.getRowCount(1));
	}

	@Test
	public void testFindPageObjects() {
//		List<PetcCommunityPO> list = petcCommunityDao.findPageObjects(1, 0, 10);
//		System.out.println(list.size());
//		System.out.println(list.toString());
	}
}
