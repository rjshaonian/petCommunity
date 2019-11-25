package com.tedu.petCommunity.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tedu.petCommunity.sys.entity.PetcActivityPO;

@Mapper
public interface PetcActivityDao {

	int getRowCount(@Param("userId")Integer  userId);
	
	List<PetcActivityPO> findActivitys( 
			  @Param("userId")Integer  userId,
		      @Param("startIndex")Integer startIndex,
		      @Param("pageSize")Integer pageSize);
}
