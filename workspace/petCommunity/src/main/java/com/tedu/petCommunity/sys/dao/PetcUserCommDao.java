package com.tedu.petCommunity.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PetcUserCommDao {
	/**
	 * @param userId
	 */
	@Delete("delete from user_comm where user_id=#{userId}")
	int deleteObjectsByUserId(Integer userId);

	/**
	 * @param userId
	 * @param commName
	 */
	int insertObjects(@Param("userId") Integer userId, @Param("commName") String commName);

	/**
	 * @param userId
	 * @param commId
	 */
	int addC(@Param("userId") Integer userId, @Param("commId") Integer[] commId);

	/**
	 * @param id
	 */
	List<Integer> findByCommId(Integer id);

	/**
	 * @param userId
	 */
	@Delete("delete from user_comm where user_id=#{userId}")
	int deleteByUserId(Integer userId);

	/**
	 * @param commId
	 */
	@Delete("delete from user_comm where comm_id=#{commId}")
	void deleteUserCommByCommId(Integer commId);

	/**
	 * @param userId
	 * @param commId
	 */
	@Delete("delete from user_comm where user_id=#{userId} and comm_id=#{commId}")
	void deleteUserCommByUserCommId(Integer userId, Integer commId);

	/**
	 * @param userId
	 * @param commId
	 */
	@Insert("insert into user_comm values (null,#{userId},#{commId})")
	void insertUserCommByUserCommId(Integer userId, Integer commId);

}
