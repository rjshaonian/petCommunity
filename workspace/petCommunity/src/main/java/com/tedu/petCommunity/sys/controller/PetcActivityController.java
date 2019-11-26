package com.tedu.petCommunity.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.petCommunity.common.util.ShiroUtils;
import com.tedu.petCommunity.common.vo.JsonResult;
import com.tedu.petCommunity.sys.entity.PetcActivityPO;
import com.tedu.petCommunity.sys.service.PetcActivityService;
import com.tedu.petCommunity.sys.vo.PetcActiDetailVO;

@Controller
@RequestMapping()
public class PetcActivityController {
	@Autowired
	PetcActivityService activityService;

	@RequestMapping("/acti_search")
	public String doActiSearchUI(Model model) {
		model.addAttribute("user", ShiroUtils.getUser());
		return "petc_acti_search";
	}

	@RequestMapping("/acti_create")
	public String doActiCreateUI() {
		return "petc_acti_create";
	}

	@RequestMapping("/acti_detail")
	public String doActiDetailUI(Integer id, Model model) {
		PetcActiDetailVO vo = activityService.getActiDetailVO(id);
		model.addAttribute("vo", vo);
		return "petc_acti_detail";
	}

	@ResponseBody
	@RequestMapping("/acti_search/loadActi")
	public JsonResult loadActi(String actiName) {
		List<PetcActivityPO> list = activityService.loadActi(actiName);
		return new JsonResult(list);
	}

	@ResponseBody
	@RequestMapping("/acti_create/doCreate")
	public JsonResult doCreateActi(String actiName, String position, String actiType, String datetimeStart,
			String datetimeEnd) {
		activityService.doCreateActi(actiName, position, actiType, datetimeStart, datetimeEnd);
		return new JsonResult("create ok");
	}

	@ResponseBody
	@RequestMapping("/acti_detail/doJoin")
	public JsonResult doJoin(Integer actiId) {
		activityService.doJoin(actiId);
		return new JsonResult("join ok");
	}

	@ResponseBody
	@RequestMapping("/acti_detail/doExit")
	public JsonResult doExit(Integer actiId) {
		activityService.doExit(actiId);
		return new JsonResult("exit ok");
	}

	@ResponseBody
	@RequestMapping("/acti_detail/doDisband")
	public JsonResult doDisband(Integer actiId) {
		activityService.doDisband(actiId);
		return new JsonResult("disband ok");
	}

	@ResponseBody
	@RequestMapping("/acti_detail/doModify")
	public JsonResult doModify(Integer actiId, String actiName, String position, String datetimeStart,
			String datetimeEnd) {
		activityService.doModify(actiId, actiName, position, datetimeStart, datetimeEnd);
		return new JsonResult("modify ok");
	}

	@RequestMapping("/acti_detail/loadContent")
	@ResponseBody
	public JsonResult loadContent(Integer actiId) {
		return new JsonResult(activityService.getUsersByActiId(actiId));
	}
//	@Autowired
//	PetcActivityService ADS;
//
//	@RequestMapping("/activity/update")
//	@ResponseBody
//	public JsonResult doUpdateObject(PetcActivityPO entity) {
//		activityService.update(entity);
//		return new JsonResult("update ok");
//	}
//
//	@ResponseBody
//	@RequestMapping("/activity/killActivity")
//	public JsonResult doDeleteObject(Integer id) {
//		ADS.killActivity(id);
//		return new JsonResult("delete ok");
//	}
//
//	@ResponseBody
//	@RequestMapping("/activity/findActivities")
//	public JsonResult doFindPageObjects(String actiName, Integer pageCurrent) {
//		return new JsonResult(activityService.findPageObjects(actiName, pageCurrent));
//	}
//
//	@ResponseBody
//	@RequestMapping("/activity/findActivityById")
//	public JsonResult doFindObjectById(Integer id) {
//		return new JsonResult(activityService.findActivityById(id));
//	}
//
//	@RequestMapping("/activity/save")
//	@ResponseBody
//	public JsonResult doSaveObject(PetcActivityPO entity/* , Integer[] Ids */) {
//		System.out.println("============================" + entity);
//		activityService.save(entity);
//		return new JsonResult("save ok");
//	}
//
//	@Autowired
//	private PetcActivityService activityService;
//
//	@ResponseBody
//	@RequestMapping("/activity/doFindActivitys")
//	public JsonResult doFindActivitys(Integer userId, Integer pageCurrent) {
//
//		PageObject<PetcActivityPO> result = activityService.findActivitys(userId, pageCurrent);
//
//		return new JsonResult(result);
//	}
}
