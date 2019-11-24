package com.tedu.petCommunity.dailyreport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tedu.petCommunity.common.vo.JsonResult;
import com.tedu.petCommunity.dailyreport.service.ActivityService;
import com.tedu.petCommunity.dailyreport.vo.PageObject;
import com.tedu.petCommunity.sys.entity.PetcActivityPO;

@RestController
@RequestMapping("/activity/")
public class ActivityControllerDao {

	@Autowired
	private ActivityService activityService;
	
	@RequestMapping("doFindActivitys")
	public JsonResult doFindActivitys(Integer userId,Integer pageCurrent) {
		
	 PageObject<PetcActivityPO> result = activityService.findActivitys(userId, pageCurrent);
	 
		return new JsonResult(result);
	}
}
