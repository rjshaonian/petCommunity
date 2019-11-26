/**
 * 
 */
package com.tedu.petCommunity.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tedu.petCommunity.sys.entity.PetcChatPO;
import com.tedu.petCommunity.sys.entity.PetcCommunityPO;
import com.tedu.petCommunity.sys.entity.PetcUserChatVO;

/**
 * @author VictorHe 2019年11月24日 下午2:37:03
 */
@Mapper
public interface PetcChatDao {
	int insertObject(PetcChatPO entity);

	int getRowCount(@Param("commId") Integer commId);

	List<PetcUserChatVO> findPageObjects(@Param("commId") Integer commId, @Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);

	/**
	 * @param id
	 * @return
	 */
	@Select("select * from community where id=#{id}")
	PetcCommunityPO getCommById(Integer id);

	/**
	 * @param commId
	 * @return
	 */
	List<PetcUserChatVO> findUserChatByCommId(String commId);

	/**
	 * @param po
	 */
	@Insert("insert into chat values(null,#{commId},#{content},#{valid},#{createdTime},#{createdUser})")
	void insertChatMessage(PetcChatPO po);
}
