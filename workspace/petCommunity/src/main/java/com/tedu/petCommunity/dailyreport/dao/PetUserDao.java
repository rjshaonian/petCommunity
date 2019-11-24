package com.tedu.petCommunity.dailyreport.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tedu.petCommunity.sys.entity.PetcUserPO;

@Mapper
public interface PetUserDao {

	@Select("select *from user where id=#{userId}") 
	PetcUserPO fingUserById(Integer userId);
	
}
