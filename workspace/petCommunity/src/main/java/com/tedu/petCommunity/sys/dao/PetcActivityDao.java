package com.tedu.petCommunity.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tedu.petCommunity.sys.entity.PetcActivityPO;

@Mapper
public interface PetcActivityDao {

	int getRowCount(@Param("actiName") String actiName);

	List<PetcActivityPO> findActivities(@Param("actiName") String actiName, @Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);

	PetcActivityPO findActivityById(Integer id);

	int insert(PetcActivityPO entity);

	int getRowCount1(@Param("userId") Integer userId);

	List<PetcActivityPO> findActivitys(@Param("userId") Integer userId, @Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);
}
