package com.tedu.petCommunity.sys.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tedu.petCommunity.common.exception.ServiceException;
import com.tedu.petCommunity.common.vo.JsonResult;
import com.tedu.petCommunity.sys.code.SendSmsDemo;



@RestController
@RequestMapping("/msg/")
public class UtilController {
	@Autowired
	private SendSmsDemo sendSmsDemo;
	@RequestMapping("send")
	public JsonResult dosend(String mobile,HttpServletRequest request) {
		HttpSession session = request.getSession();
		HashMap<Object, Object> CMap = new HashMap<>();
		CMap.put("date", new Date(0));
		String sixNum = sendSmsDemo.sendSms(mobile);
		if(sixNum==null||sixNum.isEmpty())
			throw new ServiceException("短信服务异常");
		CMap.put("sixNum", sixNum);
		session.setAttribute("CMap", CMap);
		return new JsonResult("执行成功");
	}
}
