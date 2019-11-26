/**
 * 
 */
package com.tedu.petCommunity.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.petCommunity.common.vo.JsonResult;
import com.tedu.petCommunity.sys.entity.PetcCommunityPO;
import com.tedu.petCommunity.sys.service.PetcChatService;

/**
 * @author VictorHe 2019年11月23日 上午10:06:31
 */
@Controller
//@RequestMapping("/")
@RequestMapping("/chat")
public class PetcChatController {
	@Autowired
	PetcChatService chatService;

	@RequestMapping()
	public String doChatUI(Integer id, Model model) {
		PetcCommunityPO commPO = chatService.getCommById(id);
		model.addAttribute("commPO", commPO);
		model.addAttribute("id", id);
		return "petc_chat";
	}

	@RequestMapping("/loadContent")
	@ResponseBody
	public JsonResult loadContent(String commId) {
		return new JsonResult(chatService.getContentByCommId(commId));
	}

	@RequestMapping("/sendMsg")
	@ResponseBody
	public JsonResult sendMsg(Integer commId, String chatMessage) {
		chatService.insertChatMessage(commId, chatMessage);
		return new JsonResult("insert ok");
	}
//	@Autowired
//	PetcCommChatService petcCommChatService;
//
//	@RequestMapping("chat")
//	public String doCommChatlUI(Integer id, Integer PageCurrent, Model model) {
//		PageObject<PetcUserChatVO> chats = petcCommChatService.findPageObjects(id, PageCurrent);
//		PetcCommunityPO commPO = petcCommChatService.getCommById(id);
//		model.addAttribute("chats", chats);
//		model.addAttribute("commPO", commPO);
//		return "chat/community_chat";
//	}

}
