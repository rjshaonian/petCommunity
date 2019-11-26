package com.tedu.petCommunity.sys.service.impl;

import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tedu.petCommunity.common.exception.ServiceException;
import com.tedu.petCommunity.common.util.ShiroUtils;
import com.tedu.petCommunity.sys.dao.PetcUserDao;
import com.tedu.petCommunity.sys.entity.PetcUserPO;
import com.tedu.petCommunity.sys.service.PetcPersonalService;
import com.tedu.petCommunity.sys.vo.PetcPersonalVO;

@Service
public class PetcPersonalServiceImpl implements PetcPersonalService {
	@Autowired
	private PetcUserDao petcUserDao;

	@Override
	public void doModifyPwd(String oldPwd, String newPwd) {
		// 1.非空校验
		if (StringUtils.isEmpty(newPwd))
			throw new ServiceException("新密码不能为空");
		if (StringUtils.isEmpty(oldPwd))
			throw new ServiceException("原密码不能为空");
		// 2. 校验旧密码正确性
		PetcUserPO user = (PetcUserPO) SecurityUtils.getSubject().getPrincipal();// 登录用户
		SimpleHash sh = new SimpleHash("MD5", oldPwd, user.getSalt(), 1);// 对输入密码加密运算
		if (!user.getPassword().equals(sh.toHex()))
			throw new ServiceException("原密码不正确");
		// 3.对新密码进行加密
		String salt = UUID.randomUUID().toString();
		sh = new SimpleHash("MD5", newPwd, salt, 1);
		// 4.将新密码加密以后的结果更新到数据库
		int rows = petcUserDao.updatePassword(sh.toHex(), salt, user.getId());
		if (rows == 0)
			throw new ServiceException("修改失败");
	}

	@Override
	public void doModifyInfo(PetcUserPO po) {
		if (po == null)
			throw new ServiceException("保存对象不能为空");
		po.setId(ShiroUtils.getUserId());
		petcUserDao.updateUserInfo(po);
	}

	@Override
	public PetcPersonalVO getUserById(Integer id) {
		// 1.校验参数非空
		if (id == null || id < 1)
			id = ShiroUtils.getUserId();
		// 2.查询数据
		PetcUserPO user = petcUserDao.getUserById(id);
		Integer myId = ShiroUtils.getUserId();
		int relationship = 0;
		if (user.getId().intValue() == myId) {
			relationship = 2;
		} else if (petcUserDao.getUserSubscribeByIds(myId, id) > 0) {
			relationship = 1;
		}
		// 3.组织返回参数
		PetcPersonalVO vo = new PetcPersonalVO();
		vo.setUser(user);
		vo.setRelationship(relationship);
		return vo;
	}

	@Override
	public void unsubscribe(Integer userId) {
		// 1.校验参数非空
		if (userId == null || userId < 1)
			throw new ServiceException("参数有误");
		// 2.删除subscribe表中数据
		petcUserDao.deleteSubscribeByIds(ShiroUtils.getUserId(), userId);
	}

	@Override
	public void subscribe(Integer userId) {
		// 1.校验参数非空
		if (userId == null || userId < 1)
			throw new ServiceException("参数有误");
		// 2.插入subscribe表中数据
		petcUserDao.addSubscribeByIds(ShiroUtils.getUserId(), userId);
	}
}
