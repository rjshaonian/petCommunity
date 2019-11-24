package com.tedu.petCommunity.dailyreport.service;


import com.tedu.petCommunity.dailyreport.vo.PageObject;
import com.tedu.petCommunity.sys.entity.PetcCommunityPO;

public interface CommunityService {

	PageObject<PetcCommunityPO> findCommunitys(Integer userId,Integer pageCurrent);
}
