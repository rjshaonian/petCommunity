package com.tedu.petCommunity.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

	int updatePassword(@Param("password") String password, @Param("salt") String salt, @Param("id") Integer id);

	/** 添加用户 */
	int insertAll(PetcUserPO data);

	/** 判断用户名是否存在 */
	int existName(String username);

	/**
	 * @param id
	 * @return
	 */
	@Select("select * from user where id=#{id}")
	PetcUserPO getUserById(Integer id);

	/**
	 * @param userId
	 * @param id
	 * @return
	 */
	@Select("select count(*) from subscribe where user_id=#{userId} and subscribe_user=#{subscribeUser}")
	int getUserSubscribeByIds(Integer userId, Integer subscribeUser);

	/**
	 * @param userId
	 * @param userId2
	 */
	@Delete("delete from subscribe where user_id=#{userId} and subscribe_user=#{subscribeUser}")
	void deleteSubscribeByIds(Integer userId, Integer subscribeUser);

	/**
	 * @param userId
	 * @param userId2
	 */
	@Insert("insert into subscribe values (null,#{userId},#{subscribeUser})")
	void addSubscribeByIds(Integer userId, Integer subscribeUser);

	/**
	 * @param username
	 */
	@Select("select * from user where username=#{username}")
	List<PetcUserPO> getUserByUsername(String username);
}
