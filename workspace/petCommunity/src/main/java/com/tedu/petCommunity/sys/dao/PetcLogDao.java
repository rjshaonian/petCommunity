package com.tedu.petCommunity.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.tedu.petCommunity.sys.entity.PetcLogPO;

/**
 * @author 阳昊 下午4:14:12
 */

@Mapper
public interface PetcLogDao {

	/**
	 * 用于持久化用户行为日志信息
	 * 
	 * @param logPO
	 * @return
	 */
	int insertObject(PetcLogPO logPO);

}
