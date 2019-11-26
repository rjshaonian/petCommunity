package com.tedu.petCommunity.sys.service;

import com.tedu.petCommunity.sys.entity.PetcUserPO;
import com.tedu.petCommunity.sys.vo.PetcPersonalVO;

public interface PetcPersonalService {

	void doModifyPwd(String oldPwd, String newPwd);

	void doModifyInfo(PetcUserPO po);

	/**
	 * @param id
	 * @return
	 */
	PetcPersonalVO getUserById(Integer id);

	/**
	 * @param userId
	 */
	void unsubscribe(Integer userId);

	/**
	 * @param userId
	 */
	void subscribe(Integer userId);

}
