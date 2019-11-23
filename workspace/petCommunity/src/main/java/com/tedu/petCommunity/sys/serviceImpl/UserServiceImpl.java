package com.tedu.petCommunity.sys.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tedu.petCommunity.common.exception.ServiceException;
import com.tedu.petCommunity.sys.dao.PetcUserDao;
import com.tedu.petCommunity.sys.entity.PetcUserPO;
import com.tedu.petCommunity.sys.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private PetcUserDao petcUserDao;
	@Override
	public int updatePetcUser(PetcUserPO petcUserPO, Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	@Override
	public int insertAll(PetcUserPO data) {
		//参数校验
		if(data == null)
			throw new ServiceException("参数不能为空");
		if(StringUtils.isEmpty(data.getUsername()))
			throw new ServiceException("用户名不能为空");
		if(StringUtils.isEmpty(data.getPassword()))
			throw new ServiceException("密码不能为空");
		if(StringUtils.isEmpty(data.getMobile()))
			throw new ServiceException("手机号码不能为空");
		
		//验证用户已存在
		PetcUserPO findName = petcUserDao.existName(data.getUsername());
		if(findName!=null)
			throw new ServiceException("用户名已存在");
		int rows = petcUserDao.insertAll(data);
		if(rows<1)
			throw new ServiceException("注册用户失败");
		return rows;
		
	}
	
	
}
