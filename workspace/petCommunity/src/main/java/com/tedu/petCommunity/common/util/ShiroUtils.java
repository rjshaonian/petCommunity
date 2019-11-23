package com.tedu.petCommunity.common.util;

import org.apache.shiro.SecurityUtils;

import com.tedu.petCommunity.sys.entity.PetcUserPO;

/**
 * @author 阳昊 2019年11月22日 下午4:01:14
 */
public class ShiroUtils {
	public static String getUsername() {
		try {
			return getUser().getUsername();
		} catch (Exception e) {
			return null;
		}

	}

	public static Integer getUserId() {
		try {
			return getUser().getId();
		} catch (Exception e) {
			return null;
		}
	}

	/** 获取登录用户 */
	public static PetcUserPO getUser() {
		try {
			return (PetcUserPO) SecurityUtils.getSubject().getPrincipal();
		} catch (Exception e) {
			return new PetcUserPO();
		}
	}
}
