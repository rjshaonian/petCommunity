/**
 * 
 */
package com.tedu.petCommunity.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.petCommunity.common.vo.JsonResult;
import com.tedu.petCommunity.sys.entity.PetcUserPO;
import com.tedu.petCommunity.sys.service.UserService;

/**
 * @author 彬彬
 *	上午11:37:16
 *
 */
@Controller
public class PetcRegisterController {
	@Autowired
	UserService registerService;
	@RequestMapping("register")
	public String doRegisterUI() {
		return "register/register";
	}
	
	@RequestMapping("doRegister")
	@ResponseBody
	public JsonResult doRegister(PetcUserPO data) {
		registerService.insertAll(data);
		return new JsonResult("seccess");
	}
	
}
