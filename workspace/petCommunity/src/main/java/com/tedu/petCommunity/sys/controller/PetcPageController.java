package com.tedu.petCommunity.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * : 主页url home
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/")
public class PetcPageController {

//	@RequestMapping("home")
//	public String goHome() {
//		return "home";
//	}

	@RequestMapping("activity")
	public String goActivity() {
		return "activity";
	}

	@RequestMapping("activity/activities")
	public String showActivities() {
		return "activity/activities";
	}

	@RequestMapping("activity/createActivity")
	public String goCreateActivity() {
		return "activity/createActivity";
	}

	@RequestMapping("activity/detail")
	public String goDetail() {
		return "activity/detail";
	}
//
//	@RequestMapping("doPageUI")
//	public String doPageUI() {
//		return "activity/page";
//	}
//
//	@RequestMapping("{module}/{moduleUI}")
//	public String doModuleUI(@PathVariable String moduleUI) {
//		return "activity/" + moduleUI;
//	}
}
