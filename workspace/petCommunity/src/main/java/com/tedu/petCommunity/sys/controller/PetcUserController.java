package com.tedu.petCommunity.sys.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tedu.petCommunity.common.vo.JsonResult;
import com.tedu.petCommunity.sys.entity.PetcUserPO;
import com.tedu.petCommunity.sys.service.UserService;

/**
 * @author Liam-顺
 * @createdTime 创建时间：2019年11月23日 上午10:18:29
 */
@Controller
public class PetcUserController {
	@Autowired
	private UserService userService;

	@RequestMapping("doFindUserById")
	public JsonResult doFindUserById(Integer id) {
		Map<String, Object> map = userService.findUserInfoById(id);
		return new JsonResult(map);
	}

	@RequestMapping("doUpdateUser")
	public JsonResult doUpdateUser(PetcUserPO user, Integer userId) {
		userService.updatePetcUser(user, userId);
		return new JsonResult("update ok");
	}
}
