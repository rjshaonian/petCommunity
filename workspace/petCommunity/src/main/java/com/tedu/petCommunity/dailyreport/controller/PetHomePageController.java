package com.tedu.petCommunity.dailyreport.controller;



import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/")
public class PetHomePageController {
	
	
	@RequestMapping("home")
	public String doPetCommunity() {
		return "top";
	}
	
	@RequestMapping("community/community_list")
	public String doLoadCommunityPage() {
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
