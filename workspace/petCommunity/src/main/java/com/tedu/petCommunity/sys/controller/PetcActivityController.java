package com.tedu.petCommunity.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tedu.petCommunity.common.vo.JsonResult;
import com.tedu.petCommunity.common.vo.PageObject;
import com.tedu.petCommunity.sys.entity.PetcActivityPO;
import com.tedu.petCommunity.sys.service.PetcActivityService;

@RestController
@RequestMapping("/activity/")
public class PetcActivityController {

	@Autowired
	private PetcActivityService ADS;

	@RequestMapping("killActivity")
	public JsonResult doDeleteObject(Integer id) {
		ADS.killActivity(id);
		return new JsonResult("delete ok");
	}

	@RequestMapping("findActivities")
	public JsonResult doFindPageObjects(String actiName, Integer pageCurrent) {
		return new JsonResult(ADS.findPageObjects(actiName, pageCurrent));
	}

	@RequestMapping("findActivityById")
	public JsonResult doFindObjectById(Integer id) {
		return new JsonResult(ADS.findActivityById(id));
	}

	@RequestMapping("save")
	@ResponseBody
	public JsonResult doSaveObject(PetcActivityPO entity/* , Integer[] Ids */) {
		System.out.println("============================" + entity);
		ADS.save(entity);
		return new JsonResult("save ok");
	}

	private PetcActivityService activityService;

	@RequestMapping("doFindActivitys")
	public JsonResult doFindActivitys(Integer userId, Integer pageCurrent) {

		PageObject<PetcActivityPO> result = activityService.findActivitys(userId, pageCurrent);

		return new JsonResult(result);
	}
}
