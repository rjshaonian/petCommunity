package com.tedu.petCommunity.sys.service;

import javax.servlet.http.HttpServletRequest;

import com.tedu.petCommunity.sys.entity.PetcUserPO;

/**
 * 
 * @author Liam-顺
 * @createdTime 创建时间：2019年11月22日 下午3:07:26
 */

public interface UserService {

	/**
	 * 查找用户信息
	 * 
	 * @param userId
	 * @return
	 */
	PetcUserPO findUserInfoById(Integer userId);

	/** 修改用户信息 */
	int updatePetcUser(PetcUserPO petcUserPO, Integer id);

	int updatePassword(String password, String newPassword, String cfgPassword);

	void doRegister(PetcUserPO data, String code, HttpServletRequest request);

	// String isNoDifferenceOfPassword(Integer id, String password, String
	// newPassword);

}
