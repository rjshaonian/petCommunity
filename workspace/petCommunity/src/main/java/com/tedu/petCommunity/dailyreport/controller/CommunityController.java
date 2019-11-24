package com.tedu.petCommunity.dailyreport.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tedu.petCommunity.common.vo.JsonResult;
import com.tedu.petCommunity.dailyreport.service.CommunityService;
import com.tedu.petCommunity.dailyreport.vo.PageObject;
import com.tedu.petCommunity.sys.entity.PetcCommunityPO;

@RestController
@RequestMapping("/community/")
public class CommunityController {
	
	@Autowired
	private CommunityService communityService;
	
	@RequestMapping("doFindCommunitys")
	public JsonResult doFindCommunitys(Integer userId,Integer pageCurrent) {
		
	 PageObject<PetcCommunityPO> result = communityService.findCommunitys(userId, pageCurrent);
	 
		return new JsonResult(result);
	}
}
