package com.tedu.petCommunity.sys.serviceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

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
		if (petcUserPO == null)
			throw new IllegalArgumentException("保存对象不能为空");
		if (StringUtils.isEmpty(petcUserPO.getUsername()))
			throw new IllegalArgumentException("用户名不能为空");
		if (StringUtils.isEmpty(petcUserPO.getNickname()))
			throw new IllegalArgumentException("昵称不能为空");
		if (StringUtils.isEmpty(petcUserPO.getPetType()))
			throw new IllegalArgumentException("宠物类型不能为空");
		if ((Pattern.matches("\\w+@(\\w+.)+[a-z]{2,3}", petcUserPO.getEmail())))
			throw new IllegalArgumentException("输入的邮箱不合法");
		int rows = petcUserDao.updateUserInfo(petcUserPO);
		return rows;
	}

	@Override
	public Map<String, Object> findUserInfoById(Integer userId) {
		// 验证参数合法性
		if (userId == null || userId <= 0)
			throw new ServiceException("参数数据不合法,userId=" + userId);
		PetcUserPO user = petcUserDao.findUserInfo(userId);
		if (user == null)
			throw new ServiceException("用户不存在");
		Map<String, Object> map = new HashMap<>();
		map.put("user", user);
		return map;
	}
}
