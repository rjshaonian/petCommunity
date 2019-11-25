/**
 * 
 */
package com.tedu.petCommunity.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tedu.petCommunity.common.util.ShiroUtils;
import com.tedu.petCommunity.sys.service.PatcCommDetailService;
import com.tedu.petCommunity.sys.vo.PetcCommDetailVO;

/**
 * @author VictorHe 2019年11月22日 下午5:42:39
 */
@Controller
@RequestMapping("/")
public class PetcCommDetailController {
	@Autowired
	private PatcCommDetailService patcCommDetailService;

	@RequestMapping("community/detail")
	public String doCommDetailUI(Integer user, Model model) {
		List<PetcCommDetailVO> commDetail = patcCommDetailService.getCommDetail(null, user);
		model.addAttribute("commDetail", commDetail);
		model.addAttribute("user", ShiroUtils.getUser());
		return "community/comm_detail";
	}
}
