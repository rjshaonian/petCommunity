package com.tedu.petCommunity.dailyreport.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tedu.petCommunity.common.util.ShiroUtils;
import com.tedu.petCommunity.sys.entity.PetcUserPO;



@Controller
@RequestMapping("/")
public class PetHomePageController {
	
	
	@RequestMapping("home")
	public String doPetCommunity(Model model) {
		PetcUserPO user = ShiroUtils.getUser();
		model.addAttribute("user",user);
		return "top";
	}
	
	@RequestMapping("community/community_list")
	public String doLoadCommunityPage(Model model) {
		PetcUserPO user = ShiroUtils.getUser();
		model.addAttribute("user",user);
		return "community_list";
	}
	
	@RequestMapping("activity/activity_list")
	public String doLoadActivityPage() {
		return "activity_list";
	}
	
	
	
	@RequestMapping("doPageUI")
	public String doPageUI() {
		return "common/page";	
	}
	
}
