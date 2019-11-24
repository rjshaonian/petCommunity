package com.tedu.petCommunity.dailyreport.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tedu.petCommunity.common.vo.JsonResult;
import com.tedu.petCommunity.dailyreport.service.impl.PetUserServiceImpl;
import com.tedu.petCommunity.sys.entity.PetcUserPO;

@RestController
@RequestMapping("/user/")
public class PetUserController {

	@Autowired
	private PetUserServiceImpl perUserServiceImpl;
	
	@RequestMapping("doUpdateUser")
	public JsonResult doUpdateUser(Integer userId) {
		PetcUserPO user = perUserServiceImpl.fingUserById(userId);
		return new JsonResult(user);
	}
	
	
}
