package com.tedu.petCommunity.sys.service;


import com.tedu.petCommunity.dailyreport.vo.PageObject;
import com.tedu.petCommunity.sys.entity.PetcCommunityPO;

public interface PetcCommunityService {

	PageObject<PetcCommunityPO> findCommunitys(Integer userId,Integer pageCurrent);
}
