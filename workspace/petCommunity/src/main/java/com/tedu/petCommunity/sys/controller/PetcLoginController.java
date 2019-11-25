/**
 * 
 */
package com.tedu.petCommunity.sys.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.petCommunity.common.vo.JsonResult;

/**
 * @author 张拂为 2019年11月23日 上午9:27:00
 */
@Controller
@RequestMapping("/login")
public class PetcLoginController {
	@RequestMapping()
	public String doLoginUI() {
		return "petc_login";
	}

	@RequestMapping("/doLogin")
	@ResponseBody
	public JsonResult doLogin(String username, String password, boolean isRememberMe) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		if (isRememberMe)
			token.setRememberMe(true);
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);
		return new JsonResult("login ok");
	}

}
