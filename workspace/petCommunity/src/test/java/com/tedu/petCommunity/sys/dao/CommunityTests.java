package com.tedu.petCommunity.sys.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tedu.petCommunity.common.util.ShiroUtils;
import com.tedu.petCommunity.common.vo.PageObject;
import com.tedu.petCommunity.sys.entity.PetcActivityPO;
import com.tedu.petCommunity.sys.entity.PetcCommunityPO;
import com.tedu.petCommunity.sys.entity.PetcUserPO;
import com.tedu.petCommunity.sys.service.PetcActivityService;
import com.tedu.petCommunity.sys.service.PetcCommunityService;

@SpringBootTest
public class CommunityTests {

	@Autowired
	private PetcCommunityService communityService;

	@Autowired
	private PetcActivityService activityService;

	@Test
	public void testCommunity() {

		PageObject<PetcCommunityPO> communitys = communityService.findCommunitys(2, 1);
		System.out.println(communitys);
	}

	@Test
	public void testActivity() {
		PageObject<PetcActivityPO> activitys = activityService.findActivitys(2, 1);
		System.out.println(activitys);
	}

	@Test
	public void testUser() {
		PetcUserPO user = ShiroUtils.getUser();
		System.out.println(user);
	}
}
