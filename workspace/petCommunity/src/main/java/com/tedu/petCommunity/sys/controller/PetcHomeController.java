package com.tedu.petCommunity.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.petCommunity.common.util.ShiroUtils;
import com.tedu.petCommunity.common.vo.JsonResult;
import com.tedu.petCommunity.sys.entity.PetcUserPO;
import com.tedu.petCommunity.sys.service.PetcHomeService;

@Controller
@RequestMapping("/home")
public class PetcHomeController {

	@Autowired
	PetcHomeService homeService;

	@RequestMapping()
	public String doHomeUI(Model model) {
		PetcUserPO user = ShiroUtils.getUser();
		model.addAttribute("user", user);
		return "petc_home";
	}

	@RequestMapping("/loadContent")
	@ResponseBody
	public JsonResult loadContent(String type) {
		return new JsonResult(homeService.getContentByType(type));
	}

//	@RequestMapping("home")
//	public String doPetCommunity(Model model) {
//		PetcUserPO user = ShiroUtils.getUser();
//		model.addAttribute("user", user);
//		return "top";
//	}
//
//	@RequestMapping("community/community_list")
//	public String doLoadCommunityPage(Model model) {
//		PetcUserPO user = ShiroUtils.getUser();
//		model.addAttribute("user", user);
//		return "community_list";
//	}
//
//	@RequestMapping("activity/activity_list")
//	public String doLoadActivityPage() {
//		return "activity_list";
//	}
//
//	@RequestMapping("doPageUI")
//	public String doPageUI() {
//		return "common/page";
//	}

}
