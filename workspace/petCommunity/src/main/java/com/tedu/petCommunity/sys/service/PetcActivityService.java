package com.tedu.petCommunity.sys.service;

import com.tedu.petCommunity.common.vo.PageObject;
import com.tedu.petCommunity.sys.entity.PetcActivityPO;

public interface PetcActivityService {
	PageObject<PetcActivityPO> findPageObjects(String acti_name, Integer pageCurrent);

	PetcActivityPO findActivityById(Integer id);

	int save(PetcActivityPO entity);

	PageObject<PetcActivityPO> findActivitys(Integer userId, Integer pageCurrent);

}
