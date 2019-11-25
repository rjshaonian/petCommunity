package com.tedu.petCommunity.common.util;


import com.tedu.petCommunity.sys.entity.PetcUserPO;

/**
 * @author 阳昊 2019年11月22日 下午4:01:14
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
		try {
			PetcUserPO userPO = new PetcUserPO();
			userPO.setUsername("管理员");
			userPO.setId(0);
			return userPO;
//			return (PetcUserPO) SecurityUtils.getSubject().getPrincipal();
		} catch (Exception e) {
			PetcUserPO userPO = new PetcUserPO();
			userPO.setUsername("管理员");
			userPO.setId(0);
			return userPO;
		}
	}
}
