package com.tedu.petCommunity.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/community/")
public class PageController {

	/** 返回分页页面 */
	@RequestMapping("doPageUI")
	public String doPageUI() {
		return "page";
	}

	/** 创建社区页面 */
	@RequestMapping("create")
	public String create() {
		return "PetcCommunity";
	}

	/** 查找社区页面 */
	@RequestMapping("search")
	public String search() {
		return "findPetcCommunity";
	}
	
	/** 修改社区页面 */
	@GetMapping("modify")
	public String modify() {
		return "ModifyComm";
	}
	
}
