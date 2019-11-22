package com.tedu.petCommunity.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.tedu.petCommunity.sys.entity.PetcUserPO;

@Mapper
public interface PetcUserDao {
	/**
	 * @author Liam-顺
	 * @param userInfo
	 * @return
	 * @createdTime 创建时间：2019年11月22日 上午10:50:39
	 */
	int updateUserInfo(PetcUserPO userInfo);
}
