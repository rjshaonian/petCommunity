package com.tedu.petCommunity.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tedu.petCommunity.dailyreport.vo.UserCommVo;
import com.tedu.petCommunity.sys.entity.PetcCommunityPO;
import com.tedu.petCommunity.sys.entity.PetcUserPO;

@Mapper
public interface PetcCommunityDao {

	int getRowCount(@Param("userId") Integer userId);

	List<PetcCommunityPO> findCommunitys(@Param("userId") Integer userId, @Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);

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
	@Delete("delete from community where id=#{id}")
	int deleteComm(Integer id);

	/** 加入社区 */

	int addC(PetcCommunityPO entity);

	/** 退出社区 */
	int doExit(Integer commId);

	/**
	 * @param id
	 * @return
	 */
	@Select("select * from community where id=#{id}")
	PetcCommunityPO getCommunityById(Integer id);

	/**
	 * @return
	 */
	@Select("select * from user where id=#{id}")
	PetcUserPO getUserById(Integer id);

	/**
	 * @param id
	 * @param myId
	 */
	@Select("select count(*) from user_comm where comm_id=#{commId} and user_id=#{userId}")
	int getUserCommCount(Integer commId, Integer userId);

	/**
	 * @param po
	 */
	@Insert("insert into community values(null,#{commName},#{position},#{valid},#{createdTime},#{modifiedTime},#{createdUser},#{modifiedUser})")
	int insertCommPO(PetcCommunityPO po);

	/**
	 * @param po
	 * @return
	 */
	@Select("select id from community where comm_name=#{commName} and position=#{position}")
	Integer findCommIdByPO(PetcCommunityPO po);

	/**
	 * @param userId
	 * @param commId
	 */
	@Insert("insert into user_comm values(null,#{userId},#{commId})")
	void insertRelationshipByUserComm(Integer userId, Integer commId);

}
