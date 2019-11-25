package com.tedu.petCommunity.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tedu.petCommunity.common.vo.JsonResult;
import com.tedu.petCommunity.dailyreport.vo.PageObject;
import com.tedu.petCommunity.sys.entity.PetcActivityPO;
import com.tedu.petCommunity.sys.service.PetcActivityService;

@RestController
@RequestMapping("/activity/")
public class PetcActivityController {

	@Autowired
	private PetcActivityService activityService;
	
	@RequestMapping("doFindActivitys")
	public JsonResult doFindActivitys(Integer userId,Integer pageCurrent) {
		
	 PageObject<PetcActivityPO> result = activityService.findActivitys(userId, pageCurrent);
	 
		return new JsonResult(result);
	}
}
