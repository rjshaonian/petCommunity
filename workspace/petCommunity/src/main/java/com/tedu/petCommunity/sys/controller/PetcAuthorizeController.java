package com.tedu.petCommunity.sys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tedu.petCommunity.sys.service.GithubUserService;

/**
 * @author 阳昊 2019年11月27日 下午2:58:37
 */
@Controller
public class PetcAuthorizeController {

	@Autowired
	private GithubUserService githubUserService;

	@GetMapping("/callback")
	public String callback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state,
			HttpServletRequest request, HttpServletResponse response) {
		githubUserService.callback(code, state, request, response);

		return "redirect:/home";
	}
}
