package com.tedu.petCommunity.sys.dao;

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

	/** 加入 */
	int addC(@Param("userId") Integer userId, @Param("commId") Integer[] commId);

	List<Integer> findByCommId(Integer id);

	@Delete("delete from user_comm where user_id=#{userId}")
	int deleteByUserId(Integer userId);

	@Delete("delete from user_comm where comm_id=#{commId}")
	void deleteUserCommByCommId(Integer commId);

	@Delete("delete from user_comm where user_id=#{userId} and comm_id=#{commId}")
	void deleteUserCommByUserCommId(Integer userId, Integer commId);

}
