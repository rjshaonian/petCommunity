package com.tedu.petCommunity.dailyreport.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tedu.petCommunity.common.config.PageProperties;
import com.tedu.petCommunity.common.exception.ServiceException;
import com.tedu.petCommunity.common.vo.CommObject;
import com.tedu.petCommunity.dailyreport.dao.CommActi;
import com.tedu.petCommunity.dailyreport.dao.PetcCommunityDao;
import com.tedu.petCommunity.dailyreport.dao.PetcUserCommDao;
import com.tedu.petCommunity.dailyreport.vo.UserCommVo;
import com.tedu.petCommunity.sys.entity.PetcCommunityPO;

@Service
public class PetcCommunityServiceImpl {

	@Autowired
	PetcCommunityDao petcCommunityDao;
	@Autowired
	private PageProperties pageProperties;
	@Autowired
	PetcUserCommDao petcUserCommDao;
	@Autowired
	CommActi commActi;

	/* 创建社区 **/
	public int addObject(PetcCommunityPO entity) {
		// 1.参数校验
		if (entity == null)
			throw new IllegalArgumentException("保存对象不能为空");

		if (StringUtils.isEmpty(entity.getCommName()))
			throw new IllegalArgumentException("用户名不能为空");

		// 2.保存社区信息
		// 获取当前时间并赋值给pojo对象
		entity.setCreateTime(new Date());
		entity.setModifiedTime(entity.getCreateTime());
		int rows = petcCommunityDao.createObject(entity);

		// 3.返回结果
		return rows;
	}

	/** 查找社区 */

	public CommObject<PetcCommunityPO> findC(String commName, Integer pageCurrent) {
		// 1.验证参数的合法性
		if (pageCurrent == null || pageCurrent < 1)
			throw new IllegalArgumentException("页码值不正确");
		// 2.查询总记录数
		int rowCount = petcCommunityDao.getRowCount(commName);
		if (rowCount == 0)
			throw new ServiceException("没有对应的记录");

		// 3.查询当前页要呈现的记录
		int pageSize = pageProperties.getPageSize();

		// System.out.println("pageSize=" + pageSize);
		int startIndex = (pageCurrent - 1) * pageSize;
		List<PetcCommunityPO> records = petcCommunityDao.FindPetcCommunity(commName, startIndex, pageSize);
		// 4.封装查询结果
		return new CommObject<>(records, rowCount, pageCurrent, pageSize);
	}

	/**
	 * 修改社区
	 * 
	 * @return
	 */

	public UserCommVo findById(Integer userId) {
		// 1.合法性验证
		if (userId == null || userId <= 0)
			throw new ServiceException("参数数据不合法,userId=" + userId);
		// 2.业务查询
		UserCommVo byId = petcCommunityDao.findObjectById(userId);
		if (byId == null)
			throw new ServiceException("此社区已经不存在");
		return byId;

	}

	/** 删除社区 */
	public int deleteComm(Integer id) {
		// 1.参数校验
		if (id == null || id < 1)
			throw new IllegalArgumentException("id值无效");
		petcUserCommDao.deleteByUserId(id);
		commActi.deleteByActiId(id);
		int rows = petcCommunityDao.deleteComm(id);
		if (rows == 0)
			throw new ServiceException("社区可能已经不存在");
		return rows;
	}

	/** 加入社区 */

	public int addOK(PetcCommunityPO entity, Integer[] commId) {
		// 1.参数校验
		if (entity == null)
			throw new ServiceException("保存对象不能为空");
		if (StringUtils.isEmpty(entity.getCommName()))
			throw new ServiceException("用户名不能为空");
		int rows = petcCommunityDao.addC(entity);
		petcUserCommDao.addC(entity.getId(), commId);
		return rows;
	}

	/** 退出社区 */
	public void deleteObject(PetcCommunityPO entity, Integer commId) {

		if (entity == null)
			throw new ServiceException("不存在");
		petcCommunityDao.deleteComm(commId);
		petcCommunityDao.deleteObject(entity);
	}

}
