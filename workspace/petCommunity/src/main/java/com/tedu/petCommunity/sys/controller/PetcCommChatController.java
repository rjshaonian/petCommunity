/**
 * 
 */
package com.tedu.petCommunity.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author VictorHe 2019年11月23日 上午10:06:31
 */
@Controller
@RequestMapping("/")
public class PetcCommChatController {
	@RequestMapping("chat/community_chat")
	public String doCommChatlUI() {
		return "chat/community_chat";
	}
}
