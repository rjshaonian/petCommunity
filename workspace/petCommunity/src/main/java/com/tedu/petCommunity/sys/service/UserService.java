package com.tedu.petCommunity.sys.service;

import java.util.Map;

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
	Map<String, Object> findUserInfoById(Integer userId);

	/** 修改用户信息 */
	int updatePetcUser(PetcUserPO petcUserPO, Integer id);
	
	
	int insertAll(PetcUserPO data);
}
