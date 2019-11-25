package com.tedu.petCommunity.dailyreport.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PetcUserCommDao {

	@Delete("delete from user_comm where user_id=#{userId}")
	int deleteObjectsByUserId(Integer userId);

	/** 创建 */
	int insertObjects(@Param("userId") Integer userId, @Param("commName") String commName);

	@Delete("delete from user_comm where user_id=#{userId}")
	int deleteByUserId(Integer userId);

	/** 加入 */
	int addC(@Param("userId") Integer userId, @Param("commId") Integer[] commId);

	List<Integer> findByCommId(Integer id);

}
