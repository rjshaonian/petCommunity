package com.tedu.petCommunity.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.petCommunity.common.util.ShiroUtils;
import com.tedu.petCommunity.common.vo.JsonResult;
import com.tedu.petCommunity.sys.entity.PetcUserPO;
import com.tedu.petCommunity.sys.service.PetcPersonalService;

@Controller
@RequestMapping
public class PetcPersonalController {
//	@Autowired
//	private UserService userService;
	@Autowired
	private PetcPersonalService personalService;

	@RequestMapping("personal_password")
	public String doPersionalPwdUI() {
		return "petc_personal_password";
	}

	@RequestMapping("personal_modify")
	public String doPersionalMdfUI(Model model) {
		PetcUserPO user = ShiroUtils.getUser();
		model.addAttribute("user", user);
		return "petc_personal_modify";
	}

	/**
	 * 修改密码
	 * 
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	@ResponseBody
	@RequestMapping("personal_password/doModifyPwd")
	public JsonResult doModifyPwd(String oldPwd, String newPwd) {
		personalService.doModifyPwd(oldPwd, newPwd);
		return new JsonResult("modify ok");
	}

	/**
	 * 修改个人信息
	 * 
	 * @param po
	 * @return
	 */
	@ResponseBody
	@RequestMapping("personal_modify/doModifyInfo")
	public JsonResult doModifyInfo(PetcUserPO po) {
		personalService.doModifyInfo(po);
		return new JsonResult("modify ok");
	}

//	@RequestMapping("personal")
//	public String doPersionalUI(Integer id, Model model) {
//		id = (id == null || id == 0) ? 129 : id;// 顺哥测试用
//		PetcUserPO user = userService.findUserInfoById(id);
//		model.addAttribute("user", user);
//		return "userInfo";
//	}
//
//	@RequestMapping("personal/setting")
//	public String doUpdateUI(Integer id, Model model) {
//		id = 129;// 顺哥测试用
//		PetcUserPO user = userService.findUserInfoById(id);
//		model.addAttribute("user", user);
//		return "updateUserInfo";
//	}
//
//	@RequestMapping("personal/doUpdateUser")
//	public String doUpdateUser(PetcUserPO user, Integer id) {
//		userService.updatePetcUser(user, id);
//		return "redirect:/personal?id=" + id;
//	}
//
//	@RequestMapping("doUserPsUI")
//	public String doUserPsUI() {
//		// userService.updatePassword(password, newPassword, cfgPassword);
//		return "userPs/updateUserPs";
//	}
//
//	@ResponseBody
//	@RequestMapping("doUserPsUI/doUpdatePassword")
//	public JsonResult doUpdatePassword(String password, String newPwd, String cfgPwd) {
//		userService.updatePassword(password, newPwd, cfgPwd);
//		return new JsonResult("update ok");
//	}
}
