/**
 * 
 */
package com.tedu.petCommunity.sys.controller;

import java.sql.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.petCommunity.common.exception.ServiceException;
import com.tedu.petCommunity.common.vo.JsonResult;
import com.tedu.petCommunity.sys.code.SendSmsDemo;
import com.tedu.petCommunity.sys.entity.PetcUserPO;
import com.tedu.petCommunity.sys.service.UserService;

/**
 * @author 彬彬 上午11:37:16
 *
 */
@Controller
@RequestMapping("/register")
public class PetcRegisterController {
	@Autowired
	UserService registerService;

	@RequestMapping()
	public String doRegisterUI() {
		return "petc_register";
	}

	@RequestMapping("/doRegister")
	@ResponseBody
	public JsonResult doRegister(PetcUserPO data) {
		registerService.insertAll(data);
		return new JsonResult("seccess");
	}

	@Autowired
	private SendSmsDemo sendSmsDemo;

	@RequestMapping("/sendCode")
	public JsonResult doSendCode(String mobile, HttpServletRequest request) {
		HttpSession session = request.getSession();
		HashMap<Object, Object> CMap = new HashMap<>();
		CMap.put("date", new Date(0));
		String sixNum = sendSmsDemo.sendSms(mobile);
		if (sixNum == null || sixNum.isEmpty())
			throw new ServiceException("短信服务异常");
		CMap.put("sixNum", sixNum);
		session.setAttribute("CMap", CMap);
		return new JsonResult("执行成功");
	}

}
