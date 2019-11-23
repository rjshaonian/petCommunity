package com.tedu.petCommunity.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.tedu.petCommunity.sys.entity.PetcUserPO;

@Mapper
public interface PetcUserDao {
	/**
	 * @author Liam-顺
	 * @param userInfo
	 * @createdTime 创建时间：2019年11月22日 上午10:50:39 查找+用户信息
	 */
	PetcUserPO findUserInfo(Integer userId);

	int updateUserInfo(PetcUserPO userInfo);

	/** 添加用户 */
	int insertAll(PetcUserPO data);

	/** 判断用户名是否存在 */
	PetcUserPO existName(String username);
}
