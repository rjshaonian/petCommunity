package com.tedu.petCommunity.dailyreport.service;

import com.tedu.petCommunity.dailyreport.vo.PageObject;
import com.tedu.petCommunity.sys.entity.PetcActivityPO;

public interface ActivityService {
	
	PageObject<PetcActivityPO> findActivitys(Integer userId,Integer pageCurrent);

}
