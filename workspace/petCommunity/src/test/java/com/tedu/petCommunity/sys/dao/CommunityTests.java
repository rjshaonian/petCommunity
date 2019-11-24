package com.tedu.petCommunity.sys.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tedu.petCommunity.dailyreport.service.ActivityService;
import com.tedu.petCommunity.dailyreport.service.CommunityService;
import com.tedu.petCommunity.dailyreport.service.PetUserService;
import com.tedu.petCommunity.dailyreport.vo.PageObject;
import com.tedu.petCommunity.sys.entity.PetcActivityPO;
import com.tedu.petCommunity.sys.entity.PetcCommunityPO;
import com.tedu.petCommunity.sys.entity.PetcUserPO;

@SpringBootTest
public class CommunityTests {

	@Autowired
	private CommunityService communityService;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private PetUserService petUserService;
	
	@Test
	public void testCommunity() {
		
		PageObject<PetcCommunityPO> communitys = communityService.findCommunitys(2,1);
		System.out.println(communitys);
	}
	
	@Test
	public void testActivity() {
		PageObject<PetcActivityPO> activitys = activityService.findActivitys(2,1);
		System.out.println(activitys);
	}
	
	@Test
	public void testUser() {
		PetcUserPO user = petUserService.fingUserById(2);
		System.out.println(user);
	}
}

