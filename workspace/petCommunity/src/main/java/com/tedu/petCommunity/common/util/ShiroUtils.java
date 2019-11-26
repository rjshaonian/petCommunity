package com.tedu.petCommunity.common.util;

import org.apache.shiro.SecurityUtils;

import com.tedu.petCommunity.sys.entity.PetcUserPO;

/**
 * @author 阳昊 2019年11月22日 下午4:01:14
 */
public class ShiroUtils {

	public static Integer getUserId() {
		return getUser().getId();
	}

	public static String getUsername() {
		return getUser().getUsername();
	}

	/** 获取登录用户 */
	public static PetcUserPO getUser() {
		PetcUserPO user = (PetcUserPO) SecurityUtils.getSubject().getPrincipal();
		// 当用户昵称为空时，默认将用户名赋值给昵称
		String nickname = user.getNickname();
		if (nickname == null || nickname.equals("")) {
			user.setNickname(user.getUsername());
		}
		return user;
	}
}
