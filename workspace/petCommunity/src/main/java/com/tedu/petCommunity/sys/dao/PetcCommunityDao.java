package com.tedu.petCommunity.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tedu.petCommunity.sys.entity.PetcCommunityPO;

@Mapper
public interface PetcCommunityDao {

	int getRowCount(@Param("userId")Integer  userId);
	
	List<PetcCommunityPO> findCommunitys( 
			@Param("userId")Integer  userId,
		      @Param("startIndex")Integer startIndex,
		      @Param("pageSize")Integer pageSize);
}