package com.tedu.petCommunity.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tedu.petCommunity.sys.entity.PetcUserPO;

/**
 * @author 阳昊 2019年11月23日 上午8:54:11
 */
@Mapper
public interface PetcRealmUserDao {

	/**
	 * @param username
	 * @return
	 */
	@Select("select * from user where username=#{username}")
	PetcUserPO findUserByUserName(String username);

}
