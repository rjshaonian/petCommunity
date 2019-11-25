package com.tedu.petCommunity.sys.service;

import com.tedu.petCommunity.dailyreport.vo.PageObject;
import com.tedu.petCommunity.sys.entity.PetcActivityPO;

public interface PetcActivityService {
	
	PageObject<PetcActivityPO> findActivitys(Integer userId,Integer pageCurrent);

}
