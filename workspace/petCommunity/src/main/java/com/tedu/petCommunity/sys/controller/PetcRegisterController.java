/**
 * 
 */
package com.tedu.petCommunity.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 彬彬
 *	上午11:37:16
 *
 */
@Controller
public class PetcRegisterController {
	
	@RequestMapping("register")
	public String doRegisterUI() {
		return "register/register.html";
	}
}
