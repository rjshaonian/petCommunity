package com.tedu.petCommunity.common.util;

import org.apache.shiro.SecurityUtils;

import com.tedu.petCommunity.sys.entity.PetcUserPO;

/**
 * @author 阳昊 下午4:01:14
 */
public class ShiroUtils {
	public static String getUsername() {
		return getUser().getUsername();
	}

	public static Integer getUserId() {
		return getUser().getId();
	}

	/** 获取登录用户 */
	public static PetcUserPO getUser() {
		return (PetcUserPO) SecurityUtils.getSubject().getPrincipal();
	}
}
