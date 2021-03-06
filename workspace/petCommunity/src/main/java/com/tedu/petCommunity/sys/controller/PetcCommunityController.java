package com.tedu.petCommunity.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.petCommunity.common.util.ShiroUtils;
import com.tedu.petCommunity.common.vo.JsonResult;
import com.tedu.petCommunity.sys.entity.PetcCommunityPO;
import com.tedu.petCommunity.sys.service.PetcCommunityService;
import com.tedu.petCommunity.sys.vo.PetcCommDetailVO;

@RequestMapping()
@Controller
public class PetcCommunityController {
	@Autowired
	private PetcCommunityService communityService;

	@RequestMapping("/comm_detail")
	public String doCommDetailUI(Integer id, Model model) {
		PetcCommDetailVO vo = communityService.getCommDetailVO(id);
		model.addAttribute("vo", vo);
		return "petc_comm_detail";
	}

	@RequestMapping("/comm_create")
	public String doCommCreateUI() {
		return "petc_comm_create";
	}

	@RequestMapping("/comm_search")
	public String doCommSearchUI(Model model) {
		model.addAttribute("user", ShiroUtils.getUser());
		return "petc_comm_search";
	}

	@ResponseBody
	@RequestMapping("/comm_create/doCreate")
	public JsonResult doCreateComm(String commName, String position) {
		communityService.doCreateComm(commName, position);
		return new JsonResult("create ok");
	}

	@ResponseBody
	@RequestMapping("/comm_detail/doDisband")
	public JsonResult doDisband(Integer commId) {
		communityService.doDisband(commId);
		return new JsonResult("disband ok");
	}

	@ResponseBody
	@RequestMapping("/comm_detail/doExit")
	public JsonResult doExit(Integer commId) {
		communityService.doExit(commId);
		return new JsonResult("exit ok");
	}

	@ResponseBody
	@RequestMapping("/comm_search/loadComm")
	public JsonResult loadComm(String commName) {
		List<PetcCommunityPO> list = communityService.loadComm(commName);
		return new JsonResult(list);
	}

	@ResponseBody
	@RequestMapping("/comm_detail/doJoin")
	public JsonResult doJoin(Integer commId) {
		communityService.doJoin(commId);
		return new JsonResult("join ok");
	}

	@ResponseBody
	@RequestMapping("/comm_detail/doModify")
	public JsonResult doModify(Integer commId, String commName, String position) {
		communityService.doModify(commId, commName, position);
		return new JsonResult("modify ok");
	}

	@RequestMapping("/comm_detail/loadContent")
	@ResponseBody
	public JsonResult loadContent(Integer commId) {
		return new JsonResult(communityService.getUsersByCommId(commId));
	}
//	@RequestMapping("/community/doFindCommunitys")
//	public JsonResult doFindCommunitys(Integer userId, Integer pageCurrent) {
//
//		PageObject<PetcCommunityPO> result = communityService.findCommunitys(userId, pageCurrent);
//
//		return new JsonResult(result);
//	}
//
//	/** 创建社区 */
//	@RequestMapping("/petcCommunity/doCreatObject")
//	public JsonResult doCreatObject(PetcCommunityPO entity) {
//		communityService.addObject(entity);
//		return new JsonResult("创建成功!");
//
//	}
//
//	/** 查找社区 */
//
//	@GetMapping("/petcCommunity/doFindCommName")
//	public JsonResult doFindCommName(String commName, Integer pageCurrent) throws JsonProcessingException {
//		System.out.println("======");
//		CommObject<PetcCommunityPO> po = communityService.findC(commName, pageCurrent);
//		return new JsonResult(po);
//	}
//
//	/** 修改社区 */
//	@RequestMapping("/petcCommunity/quit")
//	public JsonResult doFindObjectById(Integer id) {
//
//		UserCommVo id2 = communityService.findById(id);
//
//		return new JsonResult(id2);
//	}
//
//	/** 解散社区 */
//	@RequestMapping("/petcCommunity/doDeletePC")
//	public JsonResult doDeletePC(Integer id) {
//		communityService.deleteComm(id);
//		return new JsonResult("已解散!");
//	}
//
//	/** 加入社区 */
//	@RequestMapping("/petcCommunity/doAddComm")
//	public JsonResult doAddComm(PetcCommunityPO entity, Integer[] commId) {
//		communityService.addOK(entity, commId);
//		return new JsonResult("OK!");
//	}
//
//	/** 退出社区 */
//	@RequestMapping("/petcCommunity/doExit")
//	public JsonResult doExit(PetcCommunityPO entity, Integer commId) {
//		communityService.deleteObject(entity, commId);
//		return new JsonResult("OK!");
//	}
}
