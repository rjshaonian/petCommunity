package com.tedu.petCommunity.sys.service;

import com.tedu.petCommunity.common.vo.CommObject;
import com.tedu.petCommunity.common.vo.PageObject;
import com.tedu.petCommunity.dailyreport.vo.UserCommVo;
import com.tedu.petCommunity.sys.entity.PetcCommunityPO;

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
}
