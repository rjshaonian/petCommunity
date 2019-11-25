package com.tedu.petCommunity.dailyreport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tedu.petCommunity.common.vo.CommObject;
import com.tedu.petCommunity.common.vo.JsonResult;
import com.tedu.petCommunity.dailyreport.service.impl.PetcCommunityServiceImpl;
import com.tedu.petCommunity.dailyreport.vo.UserCommVo;
import com.tedu.petCommunity.sys.entity.PetcCommunityPO;

@RequestMapping("/petcCommunity/")
@RestController
public class petcCommunityController {

	@Autowired
	PetcCommunityServiceImpl petcCommunityServiceImpl;

	/** 创建社区 */
	@RequestMapping("doCreatObject")
	public JsonResult doCreatObject(PetcCommunityPO entity) {
		petcCommunityServiceImpl.addObject(entity);
		return new JsonResult("创建成功!");

	}

	/** 查找社区 */

	@GetMapping("doFindCommName")
	public JsonResult doFindCommName(String commName, Integer pageCurrent) throws JsonProcessingException {
		System.out.println("======");
		CommObject<PetcCommunityPO> po = petcCommunityServiceImpl.findC(commName, pageCurrent);
		return new JsonResult(po);
	}

	/** 修改社区 */
	@RequestMapping("quit")
	public JsonResult doFindObjectById(Integer id) {

		UserCommVo id2 = petcCommunityServiceImpl.findById(id);

		return new JsonResult(id2);
	}

	/** 解散社区 */
	@RequestMapping("doDeletePC")
	public JsonResult doDeletePC(Integer id) {
		petcCommunityServiceImpl.deleteComm(id);
		return new JsonResult("已解散!");
	}

	/** 加入社区 */
	@RequestMapping("doAddComm")
	public JsonResult doAddComm(PetcCommunityPO entity, Integer[] commId) {
		petcCommunityServiceImpl.addOK(entity, commId);
		return new JsonResult("OK!");
	}

	/** 退出社区 */
	@RequestMapping("doExit")
	public JsonResult doExit(PetcCommunityPO entity, Integer commId) {
		petcCommunityServiceImpl.deleteObject(entity, commId);

		return new JsonResult("OK!");
	}
}
