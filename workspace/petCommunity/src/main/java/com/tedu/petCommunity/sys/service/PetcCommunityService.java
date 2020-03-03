package com.tedu.petCommunity.sys.service;

import java.util.List;

import com.tedu.petCommunity.common.vo.CommObject;
import com.tedu.petCommunity.common.vo.PageObject;
import com.tedu.petCommunity.dailyreport.vo.UserCommVo;
import com.tedu.petCommunity.sys.entity.PetcCommunityPO;
import com.tedu.petCommunity.sys.entity.PetcUserPO;
import com.tedu.petCommunity.sys.vo.PetcCommDetailVO;

public interface PetcCommunityService {

	PageObject<PetcCommunityPO> findCommunitys(Integer userId, Integer pageCurrent);

	/**
	 * @param entity
	 * @param commId
	 */
	void deleteObject(PetcCommunityPO entity, Integer commId);

	/**
	 * @param entity
	 * @param commId
	 * @return
	 */
	int addOK(PetcCommunityPO entity, Integer[] commId);

	/**
	 * @param id
	 * @return
	 */
	int deleteComm(Integer id);

	/**
	 * @param userId
	 * @return
	 */
	UserCommVo findById(Integer userId);

	/**
	 * @param commName
	 * @param pageCurrent
	 * @return
	 */
	CommObject<PetcCommunityPO> findC(String commName, Integer pageCurrent);

	/**
	 * @param entity
	 * @return
	 */
	int addObject(PetcCommunityPO entity);

	/**
	 * @param createdUser
	 * @return
	 */
	PetcUserPO getUserNameById(Integer createdUser);

	/**
	 * @param id
	 * @return
	 */
	PetcCommDetailVO getCommDetailVO(Integer id);

	/**
	 * @param commName
	 * @param position
	 */
	void doCreateComm(String commName, String position);

	/**
	 * @param commId
	 */
	void doDisband(Integer commId);

	/**
	 * @param commId
	 */
	void doExit(Integer commId);

	/**
	 * @param commName
	 * @return
	 */
	List<PetcCommunityPO> loadComm(String commName);

//	/**
//	 * @param commName
//	 * @return
//	 */
//	List<PetcCommunityPO> loadCommCache(String commName);
	/**
	 * @param commId
	 */
	void doJoin(Integer commId);

	/**
	 * @param commId
	 * @param commName
	 * @param position
	 */
	void doModify(Integer commId, String commName, String position);

	/**
	 * @param commId
	 * @return
	 */
	List<PetcUserPO> getUsersByCommId(Integer commId);

}
