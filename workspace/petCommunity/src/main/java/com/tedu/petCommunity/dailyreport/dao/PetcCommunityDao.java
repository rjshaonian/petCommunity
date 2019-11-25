package com.tedu.petCommunity.dailyreport.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tedu.petCommunity.dailyreport.vo.UserCommVo;
import com.tedu.petCommunity.sys.entity.PetcCommunityPO;

@Mapper
public interface PetcCommunityDao {

	/** 创建社区 */
	int createObject(PetcCommunityPO entity);

	/** 查找社区 */

	List<PetcCommunityPO> FindPetcCommunity(@Param("commName") String commName, // param1
			@Param("startIndex") Integer startIndex, // param2
			@Param("pageSize") Integer pageSize);// param3

	int getRowCount(@Param("commName") String commName);

	/** 修改社区 */

	UserCommVo findObjectById(Integer id);

	/** 删除社区 */
	int deleteComm(Integer id);

	/** 加入社区 */

	int addC(PetcCommunityPO entity);

	/** 退出社区 */
	int deleteObject(PetcCommunityPO entity);

}
