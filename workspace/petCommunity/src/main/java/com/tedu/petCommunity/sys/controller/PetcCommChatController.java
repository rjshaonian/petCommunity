/**
 * 
 */
package com.tedu.petCommunity.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tedu.petCommunity.sys.entity.PetcCommunityPO;
import com.tedu.petCommunity.sys.entity.PetcUserChatVO;
import com.tedu.petCommunity.sys.service.PetcCommChatService;
import com.tedu.petCommunity.sys.vo.PageObject;

/**
 * @author VictorHe 2019年11月23日 上午10:06:31
 */
@Controller
@RequestMapping("/")
public class PetcCommChatController {
	@Autowired
	PetcCommChatService petcCommChatService;

	@RequestMapping("chat")
	public String doCommChatlUI(Integer id, Integer PageCurrent, Model model) {
		PageObject<PetcUserChatVO> chats = petcCommChatService.findPageObjects(id, PageCurrent);
		PetcCommunityPO commPO = petcCommChatService.getCommById(id);
		model.addAttribute("chats", chats);
		model.addAttribute("commPO", commPO);
		return "chat/community_chat";
	}

}
