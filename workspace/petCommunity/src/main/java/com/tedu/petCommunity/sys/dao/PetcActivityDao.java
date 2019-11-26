package com.tedu.petCommunity.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tedu.petCommunity.sys.entity.PetcActivityPO;
import com.tedu.petCommunity.sys.entity.PetcUserPO;

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

	@Delete("delete from activity where id=#{id}")
	int killActivityById(Integer Id);

	int update(PetcActivityPO entity);

	/**
	 * @param actiName
	 * @return
	 */
	List<PetcActivityPO> loadActi(String actiName);

	/**
	 * @param actiName
	 * @return
	 */
	@Select("select * from activity where acti_name=#{actiName}")
	List<PetcActivityPO> findActivityByName(String actiName);

	/**
	 * @param po
	 * @return
	 */

	@Select("select id from activity where acti_name=#{actiName}")
	Integer findActiIdByPO(PetcActivityPO po);

	/**
	 * @param userId
	 * @param actiId
	 */
	@Insert("insert into user_acti values(null,#{userId},#{actiId})")
	void insertRelationshipByUserActi(Integer userId, Integer actiId);

	/**
	 * @param id
	 * @return
	 */
	@Select("select * from activity where id=#{id}")
	PetcActivityPO getActiById(Integer id);

	/**
	 * @param createdUser
	 * @return
	 */
	@Select("select * from user where id=#{id}")
	PetcUserPO getUserById(Integer id);

	/**
	 * @param id
	 * @param myId
	 * @return
	 */
	@Select("select count(*) from user_acti where acti_id=#{actiId} and user_id=#{userId}")
	int getUserActiCount(Integer actiId, Integer userId);

	/**
	 * @param myUserId
	 * @param actiId
	 */
	@Insert("insert into user_acti values (null,#{userId},#{actiId})")
	void insertUserActiByUserActiId(Integer userId, Integer actiId);

	/**
	 * @param myUserId
	 * @param actiId
	 */
	@Delete("delete from user_acti where user_id=#{userId} and acti_id=#{actiId}")
	void deleteUserActiByUserActiId(Integer userId, Integer actiId);

	/**
	 * @param actiId
	 */
	@Delete("delete from user_acti where acti_id=#{actiId}")
	void deleteUserActiByActiId(Integer actiId);

	/**
	 * @param actiId
	 */
	@Delete("delete from comm_acti where acti_id=#{actiId}")
	void deleteCommActiByActiId(Integer actiId);

	/**
	 * @param actiId
	 */
	@Delete("delete from activity where id=#{id}")
	void deleteActiById(Integer id);

	/**
	 * @param po
	 */
	@Update("update activity set acti_name=#{actiName},position=#{position},start_time=#{startTime},end_time=#{endTime},modified_time=#{modifiedTime},modified_user=#{modifiedUser} WHERE id=#{id}")
	void updateActiByPO(PetcActivityPO po);

	/**
	 * @param actiId
	 * @return
	 */
	@Select("select u.* from user u,user_acti where u.id=user_id and acti_id=#{actiId}")
	List<PetcUserPO> getUsersByActiId(Integer actiId);

}
