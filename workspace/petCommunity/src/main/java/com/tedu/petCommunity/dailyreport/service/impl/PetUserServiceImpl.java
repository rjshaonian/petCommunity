package com.tedu.petCommunity.dailyreport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tedu.petCommunity.dailyreport.dao.PetUserDao;
import com.tedu.petCommunity.dailyreport.service.PetUserService;
import com.tedu.petCommunity.sys.entity.PetcUserPO;

@Service
public class PetUserServiceImpl implements PetUserService {
	
	@Autowired
	private PetUserDao petUserDao;

	@Override
	public PetcUserPO fingUserById(Integer userId) {
		if(userId==null || userId<0)
			throw new IllegalArgumentException("id值不正确");
		PetcUserPO result = petUserDao.fingUserById(userId);
		return result;
	}

}
