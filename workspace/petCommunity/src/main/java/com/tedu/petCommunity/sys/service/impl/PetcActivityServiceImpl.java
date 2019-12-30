package com.tedu.petCommunity.sys.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.tedu.petCommunity.common.exception.ServiceException;
import com.tedu.petCommunity.common.util.ShiroUtils;
import com.tedu.petCommunity.common.vo.PageObject;
import com.tedu.petCommunity.sys.dao.PetcActivityDao;
import com.tedu.petCommunity.sys.entity.PetcActivityPO;
import com.tedu.petCommunity.sys.entity.PetcUserPO;
import com.tedu.petCommunity.sys.service.PetcActivityService;
import com.tedu.petCommunity.sys.vo.PetcActiDetailVO;

@Service
public class PetcActivityServiceImpl implements PetcActivityService {

	@Override
	public PageObject<PetcActivityPO> findPageObjects(String actiName, Integer pageCurrent) {

		// 1.对参数进行校验
		if (pageCurrent == null || pageCurrent < 1)
			throw new IllegalArgumentException("当前页码值无效");
		// 2.查询总记录数并进行校验
		int rowCount = activityDao.getRowCount(actiName);
		if (rowCount == 0)
			throw new ServiceException("没有找到对应记录");
		// 3.查询当前页记录
		int pageSize = 5;
		int startIndex = (pageCurrent - 1) * pageSize;
		List<PetcActivityPO> records = activityDao.findActivities(actiName, startIndex, pageSize);

		// 4.对查询结果进行封装并返回
		return new PageObject<>(pageCurrent, pageSize, rowCount, records);
	}

	@Override
	public PetcActivityPO findActivityById(Integer id) {
		if (id == null || id < 1)
			throw new IllegalArgumentException("id值无效");

		PetcActivityPO rm = activityDao.findActivityById(id);
		/*
		 * SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 * rm.setCreatedTime2(format.format(rm.getCreateTime()));
		 * System.err.println(rm.getCreatedTime2());
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
		int rows = activityDao.insert(entity);
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
		int rowCount = activityDao.getRowCount1(userId);
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

	@Autowired
	PetcActivityDao ADD;

	@Override
	public int killActivity(Integer Id) {
		int rows = ADD.killActivityById(Id);
		if (rows == 0)
			throw new ServiceException("此菜单可能已经不存在");
		return rows;

	}

	@Override
	public int update(PetcActivityPO entity) {
		if (entity == null)
			throw new IllegalArgumentException("修改对象不能为空");
		if (null == (entity.getActiName()))
			throw new IllegalArgumentException("活动名不允许为空");
		int rows = activityDao.update(entity);
		return rows;
	}

	@Override
	public List<PetcActivityPO> loadActi(String actiName) {
		return activityDao.loadActi(actiName);
	}

	@Override
	public void doCreateActi(String actiName, String position, String actiType, String datetimeStart,
			String datetimeEnd) {
		// 1.参数校验
		if (actiName == null || actiName == "")
			throw new ServiceException("活动名不能为空");
		List<PetcActivityPO> list = activityDao.findActivityByName(actiName);
		if (list != null && list.size() > 0)
			throw new ServiceException("活动名已存在");

		// 2.组织参数
		PetcActivityPO po = new PetcActivityPO();
		po.setActiName(actiName);
		po.setPosition(position);
		po.setActiType(actiType);
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			po.setStartTime(format.parse(datetimeStart));
			po.setEndTime(format.parse(datetimeEnd));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		po.setCreatedTime(new Date());
		po.setModifiedTime(po.getCreatedTime());
		Integer userId = ShiroUtils.getUserId();
		po.setCreatedUser(userId);
		po.setModifiedUser(po.getCreatedUser());
		// 3.数据持久化
		activityDao.insert(po);
		// 2.查出id
		Integer actiId = activityDao.findActiIdByPO(po);
		// 3.向用户、社区关系表中插入一条关联数据
		activityDao.insertRelationshipByUserActi(userId, actiId);

	}

	@Override
	public PetcActiDetailVO getActiDetailVO(Integer id) {
		// 1.根据id获取社区信息
		PetcActivityPO po = activityDao.getActiById(id);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		po.setStartTimeStr(format.format(po.getStartTime()));
		po.setEndTimeStr(format.format(po.getEndTime()));
		po.setCreatedTimeStr(format.format(po.getCreatedTime()));
		po.setModifiedTimeStr(format.format(po.getModifiedTime()));
		// 2.根据创建者id获取创建者信息
		PetcUserPO createdUser = activityDao.getUserById(po.getCreatedUser());
		// 3.分析登录账号相对此社区的类型
		Integer myId = ShiroUtils.getUserId();
		Integer relationship = 0;
		if (myId.intValue() == createdUser.getId().intValue()) {
			relationship = 2;
		} else if (activityDao.getUserActiCount(id, myId) > 0) {
			relationship = 1;
		}
		PetcActiDetailVO vo = new PetcActiDetailVO();
		vo.setId(id);
		vo.setNickname(createdUser.getNickname());
		vo.setRelationship(relationship);
		vo.setPo(po);
		return vo;
	}

	@Override
	public void doJoin(Integer actiId) {
		// 1.校验参数非空
		if (actiId == null || actiId < 1 || "".equals(actiId))
			throw new ServiceException("参数有误");
		// 2.获取当前登录用户的id
		Integer myUserId = ShiroUtils.getUserId();
		// 3.根据登录的用户id,活动社区id删除user_acticomm的数据
		activityDao.insertUserActiByUserActiId(myUserId, actiId);
	}

	@Override
	public void doExit(Integer actiId) {
		// 1.校验参数非空
		if (actiId == null || actiId < 1 || "".equals(actiId))
			throw new ServiceException("参数有误");
		// 2.获取当前登录用户的id
		Integer myUserId = ShiroUtils.getUserId();
		// 3.根据登录的用户id,活动id删除user_acti的数据
		activityDao.deleteUserActiByUserActiId(myUserId, actiId);
	}

	@Override
	@Transactional
	public void doDisband(Integer actiId) {
		// 1.校验参数
		if (actiId == null || actiId < 1)
			throw new ServiceException("参数有误");
		// 2.删除user_acti
		activityDao.deleteUserActiByActiId(actiId);
		// 3.删除comm_acti
		activityDao.deleteCommActiByActiId(actiId);
		// 4.删除activity
		activityDao.deleteActiById(actiId);

	}

	@Override
	public void doModify(Integer actiId, String actiName, String position, String datetimeStart, String datetimeEnd) {
		// 1.校验参数非空
		if (actiId == null || actiId < 1)
			throw new ServiceException("参数有误");
		if (StringUtils.isEmpty(actiName) || StringUtils.isEmpty(position) || StringUtils.isEmpty(datetimeStart)
				|| StringUtils.isEmpty(datetimeEnd))
			throw new ServiceException("参数有误");
		// 2.组织参数
		PetcActivityPO po = new PetcActivityPO();
		po.setId(actiId);
		po.setActiName(actiName);
		po.setPosition(position);
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			po.setStartTime(format.parse(datetimeStart));
			po.setEndTime(format.parse(datetimeEnd));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		po.setModifiedTime(new Date());
		po.setModifiedUser(ShiroUtils.getUserId());
		// 3.持久化数据
		activityDao.updateActiByPO(po);
	}

	@Override
	public List<PetcUserPO> getUsersByActiId(Integer actiId) {
		// 1.校验参数非空
		if (actiId == null || actiId < 1)
			throw new ServiceException("参数有误");
		// 2.查询数据
		return activityDao.getUsersByActiId(actiId);
	}
}
