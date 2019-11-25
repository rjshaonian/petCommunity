/**
 * 
 */
package com.tedu.petCommunity.sys.service;

import java.util.List;

import com.tedu.petCommunity.sys.entity.PetcCommunityPO;
import com.tedu.petCommunity.sys.entity.PetcUserChatVO;
import com.tedu.petCommunity.sys.vo.PageObject;

/**
 * @author VictorHe 2019年11月24日 下午4:42:36
 */
public interface PetcChatService {

	PageObject<PetcUserChatVO> findPageObjects(Integer commId, Integer PageCurrent);

	/**
	 * @param id
	 * @return
	 */
	PetcCommunityPO getCommById(Integer id);

	/**
	 * @param commId
	 * @return
	 */
	List<PetcUserChatVO> getContentByCommId(String commId);

	/**
	 * @param chatMessage
	 */
	void insertChatMessage(Integer commId, String chatMessage);

}
