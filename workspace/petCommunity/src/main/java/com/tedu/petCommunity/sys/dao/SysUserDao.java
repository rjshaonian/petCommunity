package com.tedu.petCommunity.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tedu.petCommunity.sys.entity.SysUser;

@Mapper
public interface SysUserDao {

	@Select("select * from sys_users where username=#{username}")
	SysUser findUserByUserName(String username);

	/**
	 * 保存用户自身信息
	 * 
	 * @param entity
	 * @return
	 */
	int insertObject(SysUser entity);
}
