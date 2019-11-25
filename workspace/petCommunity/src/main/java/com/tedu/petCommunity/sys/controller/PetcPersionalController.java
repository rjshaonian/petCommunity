package com.tedu.petCommunity.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.petCommunity.common.vo.JsonResult;
import com.tedu.petCommunity.sys.entity.PetcUserPO;
import com.tedu.petCommunity.sys.service.UserService;

@Controller
@RequestMapping("/")
public class PetcPersionalController {
	@Autowired
	private UserService userService;

	@RequestMapping("personal")
	public String doPersionalUI(Integer id, Model model) {
		id = (id == null || id == 0) ? 129 : id;// 顺哥测试用
		PetcUserPO user = userService.findUserInfoById(id);
		model.addAttribute("user", user);
		return "userInfo";
	}

	@RequestMapping("personal/setting")
	public String doUpdateUI(Integer id, Model model) {
		id = 129;// 顺哥测试用
		PetcUserPO user = userService.findUserInfoById(id);
		model.addAttribute("user", user);
		return "updateUserInfo";
	}

	@RequestMapping("personal/doUpdateUser")
	public String doUpdateUser(PetcUserPO user, Integer id) {
		userService.updatePetcUser(user, id);
		return "redirect:/personal?id=" + id;
	}

	@RequestMapping("doUserPsUI")
	public String doUserPsUI(Integer id) {

		return "userPs/userPs";
	}

	@ResponseBody
	@RequestMapping("doUserPsUI/doUpdatePassword")
	public JsonResult doUpdatePassword(String pwd, String newPwd, String cfgPwd) {
		userService.updatePassword(pwd, newPwd, cfgPwd);
		return new JsonResult("update ok");
	}
}
