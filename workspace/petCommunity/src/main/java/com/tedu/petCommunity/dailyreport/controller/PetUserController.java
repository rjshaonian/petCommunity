package com.tedu.petCommunity.dailyreport.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tedu.petCommunity.common.util.ShiroUtils;
import com.tedu.petCommunity.common.vo.JsonResult;

import com.tedu.petCommunity.sys.entity.PetcUserPO;

@RestController
@RequestMapping("/user/")
public class PetUserController {

	@RequestMapping("doUpdateUser")
	public JsonResult doUpdateUser() {
		PetcUserPO user = ShiroUtils.getUser();
		return new JsonResult(user);
	}
	
	
}
