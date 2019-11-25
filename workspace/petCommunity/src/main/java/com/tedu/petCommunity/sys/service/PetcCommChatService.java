/**
 * 
 */
package com.tedu.petCommunity.sys.service;

import com.tedu.petCommunity.sys.entity.PetcCommunityPO;
import com.tedu.petCommunity.sys.entity.PetcUserChatVO;
import com.tedu.petCommunity.sys.vo.PageObject;

/**
 * @author VictorHe 2019年11月24日 下午4:42:36
 */
public interface PetcCommChatService {

	PageObject<PetcUserChatVO> findPageObjects(Integer commId, Integer PageCurrent);

	/**
	 * @param id
	 * @return
	 */
	PetcCommunityPO getCommById(Integer id);
}
