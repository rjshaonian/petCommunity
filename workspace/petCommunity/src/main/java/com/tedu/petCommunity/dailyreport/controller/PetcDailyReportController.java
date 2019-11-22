package com.tedu.petCommunity.dailyreport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tedu.petCommunity.common.vo.JsonResult;
import com.tedu.petCommunity.dailyreport.PetcDailyReportPO;
import com.tedu.petCommunity.dailyreport.service.PetcDailyReportService;
import com.tedu.petCommunity.dailyreport.vo.PetcDailyReportVO;

/**
 * @author 阳昊 上午10:47:27
 */
@Controller
@RequestMapping("/")
public class PetcDailyReportController {

	@Autowired
	PetcDailyReportService petcDailyReportService;

	/**
	 * 功能：查询日常汇报数据，并返回前端页面 ，测试页面：http://localhost/doDailyReportUI
	 * 
	 * @param name  查询名字，空则为全部查询
	 * @param type  查询方式，0：查询当天，1：查询全部
	 * @param model 封装数据并发送到前端页面
	 * @return
	 */
	@GetMapping("doDailyReportUI")
	public String doDailyReportUI(String name, Integer type, Model model) {
		List<PetcDailyReportVO> dailyReports = petcDailyReportService.getDailyReports(name, type);
		model.addAttribute("dailyReports", dailyReports);
		model.addAttribute("name", name);
		model.addAttribute("type", type);
		return "/daily_report/daily_report";
	}

	/**
	 * 功能：删除对应id的数据，
	 * 
	 * @param id
	 * @return
	 * @throws JsonProcessingException
	 */
	@PostMapping("dailyReport/doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id) throws JsonProcessingException {
		petcDailyReportService.deleteDailyReport(id);
		return new JsonResult("delete ok");
	}

	@GetMapping("doDailyReportAddUI")
	public String doDailyReportAddUI(Model model) {
		model.addAttribute("typeStr", "新增");
		return "/daily_report/daily_report_form";
	}

	@GetMapping("doDailyReportUpdateUI")
	public String doDailyReportUpdateUI(Integer id, Model model) {
		PetcDailyReportPO dailyReportPO = petcDailyReportService.queryDailyReportById(id);
		model.addAttribute("typeStr", "修改");
		model.addAttribute("id", id);
		model.addAttribute("name", dailyReportPO.getName());
		model.addAttribute("modifiedFiles", dailyReportPO.getModifiedFile());
		model.addAttribute("description", dailyReportPO.getDescription());
		return "/daily_report/daily_report_form";
	}

	@GetMapping("doDailyReportChange")
	public String doDailyReportChange(@RequestParam("id") Integer id, @RequestParam("name") String name,
			@RequestParam("modifiedFiles") String modifiedFiles, @RequestParam("description") String description,
			Model model) {
		petcDailyReportService.setDailyReport(id, name, modifiedFiles, description);
		List<PetcDailyReportVO> dailyReports = petcDailyReportService.getDailyReports(null, null);
		model.addAttribute("dailyReports", dailyReports);
		model.addAttribute("name", null);
		model.addAttribute("type", null);
		return "redirect:/doDailyReportUI";
	}
}
