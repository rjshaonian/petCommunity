package com.tedu.petCommunity.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tedu.petCommunity.common.exception.ServiceException;
import com.tedu.petCommunity.common.vo.PageObject;
import com.tedu.petCommunity.sys.dao.PetcActivityDao;
import com.tedu.petCommunity.sys.entity.PetcActivityPO;
import com.tedu.petCommunity.sys.service.PetcActivityService;

@Service
public class PetcActivityServiceImpl implements PetcActivityService {

	@Autowired
	private PetcActivityDao ADD;

	@Override
	public PageObject<PetcActivityPO> findPageObjects(String actiName, Integer pageCurrent) {

		// 1.对参数进行校验
		if (pageCurrent == null || pageCurrent < 1)
			throw new IllegalArgumentException("当前页码值无效");
		// 2.查询总记录数并进行校验
		int rowCount = ADD.getRowCount(actiName);
		if (rowCount == 0)
			throw new ServiceException("没有找到对应记录");
		// 3.查询当前页记录
		int pageSize = 5;
		int startIndex = (pageCurrent - 1) * pageSize;
		List<PetcActivityPO> records = ADD.findActivities(actiName, startIndex, pageSize);

		// 4.对查询结果进行封装并返回
		return new PageObject<>(pageCurrent, pageSize, rowCount, records);
	}

	@Override
	public PetcActivityPO findActivityById(Integer id) {
		if (id == null || id < 1)
			throw new IllegalArgumentException("id值无效");

		PetcActivityPO rm = ADD.findActivityById(id);
		/*
		 * SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 * rm.setCreateTime2(format.format(rm.getCreateTime()));
		 * System.err.println(rm.getCreateTime2());
		 */
		if (rm == null)
			throw new ServiceException("记录可能已不存在");
		return rm;
	}

	@Override
	public int save(PetcActivityPO entity/* , Integer[] ids */) {
		System.err.println("===");
		System.out.println(entity);
		System.err.println("===");
		// 1.参数有效性校验
		if (entity == null)
			throw new IllegalArgumentException("保存对象不能为空");
		if (null == (entity.getActiName()))
			throw new IllegalArgumentException("活动名不允许为空");
		// 2.保存角色自身信息
		int rows = ADD.insert(entity);
		// 3.保存角色菜单关系数据
		/* sysRoleMenuDao.insertObjects(entity.getId(), Ids); */
		// 4.返回业务结果
		return rows;
	}

	@Autowired
	private PetcActivityDao activityDao;

	@Override
	public PageObject<PetcActivityPO> findActivitys(Integer userId, Integer pageCurrent) {

		if (pageCurrent == null || pageCurrent < 1)
			throw new IllegalArgumentException("当前页码不正确");
		int rowCount = activityDao.getRowCount(userId);
		if (rowCount == 0)
			throw new ServiceException("系统没有查到对应活动的记录");
		int pageSize = 3;
		int startIndex = (pageCurrent - 1) * pageSize;
		List<PetcActivityPO> records = activityDao.findActivitys(userId, startIndex, pageSize);
		PageObject<PetcActivityPO> pageObject = new PageObject<PetcActivityPO>();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRowCount(rowCount);
		pageObject.setRecords(records);
		pageObject.setPageCount((rowCount - 1) / pageSize + 1);
		return pageObject;
	}

}
