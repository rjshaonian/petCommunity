package com.tedu.petCommunity.sys.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tedu.petCommunity.common.config.PageProperties;
import com.tedu.petCommunity.common.exception.ServiceException;
import com.tedu.petCommunity.common.util.ShiroUtils;
import com.tedu.petCommunity.common.vo.CommObject;
import com.tedu.petCommunity.common.vo.PageObject;
import com.tedu.petCommunity.dailyreport.vo.UserCommVo;
import com.tedu.petCommunity.sys.dao.CommActi;
import com.tedu.petCommunity.sys.dao.PetcCommunityDao;
import com.tedu.petCommunity.sys.dao.PetcUserCommDao;
import com.tedu.petCommunity.sys.entity.PetcCommunityPO;
import com.tedu.petCommunity.sys.entity.PetcUserPO;
import com.tedu.petCommunity.sys.service.PetcCommunityService;
import com.tedu.petCommunity.sys.vo.PetcCommDetailVO;

@Service
public class PetcCommunityServiceImpl implements PetcCommunityService {

	@Autowired
	private PetcCommunityDao communityDao;

	@Override
	public PageObject<PetcCommunityPO> findCommunitys(Integer userId, Integer pageCurrent) {

		if (pageCurrent == null || pageCurrent < 1)
			throw new IllegalArgumentException("当前页码不正确");
		int rowCount = communityDao.getRowCount(userId);
		if (rowCount == 0)
			throw new ServiceException("系统没有查到相关社区的记录");
		int pageSize = 2;
		int startIndex = (pageCurrent - 1) * pageSize;
		List<PetcCommunityPO> records = communityDao.findCommunitys(userId, startIndex, pageSize);
		PageObject<PetcCommunityPO> pageObject = new PageObject<>();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRowCount(rowCount);
		pageObject.setRecords(records);
		pageObject.setPageCount((rowCount - 1) / pageSize + 1);

		return pageObject;
	}

	@Autowired
	private PageProperties pageProperties;
	@Autowired
	PetcUserCommDao petcUserCommDao;
	@Autowired
	CommActi commActi;

	/* 创建社区 **/
	@Override
	public int addObject(PetcCommunityPO entity) {
		// 1.参数校验
		if (entity == null)
			throw new IllegalArgumentException("保存对象不能为空");
		if (StringUtils.isEmpty(entity.getCommName()))
			throw new IllegalArgumentException("用户名不能为空");
		// 2.保存社区信息
		// 获取当前时间并赋值给pojo对象
		entity.setCreatedTime(new Date());
		entity.setModifiedTime(entity.getCreatedTime());
		int rows = communityDao.createObject(entity);
		// 3.返回结果
		return rows;
	}

	/** 查找社区 */
	@Override
	public CommObject<PetcCommunityPO> findC(String commName, Integer pageCurrent) {
		// 1.验证参数的合法性
		if (pageCurrent == null || pageCurrent < 1)
			throw new IllegalArgumentException("页码值不正确");
		// 2.查询总记录数
		int rowCount = communityDao.getRowCount(commName);
		if (rowCount == 0)
			throw new ServiceException("没有对应的记录");

		// 3.查询当前页要呈现的记录
		int pageSize = pageProperties.getPageSize();

		// System.out.println("pageSize=" + pageSize);
		int startIndex = (pageCurrent - 1) * pageSize;
		List<PetcCommunityPO> records = communityDao.FindPetcCommunity(commName, startIndex, pageSize);
		// 4.封装查询结果
		return new CommObject<>(records, rowCount, pageCurrent, pageSize);
	}

	/**
	 * 修改社区
	 * 
	 * @return
	 */
	@Override
	public UserCommVo findById(Integer userId) {
		// 1.合法性验证
		if (userId == null || userId <= 0)
			throw new ServiceException("参数数据不合法,userId=" + userId);
		// 2.业务查询
		UserCommVo byId = communityDao.findObjectById(userId);
		if (byId == null)
			throw new ServiceException("此社区已经不存在");
		return byId;

	}

	/** 删除社区 */
	@Override
	public int deleteComm(Integer id) {
		// 1.参数校验
		if (id == null || id < 1)
			throw new IllegalArgumentException("id值无效");
		petcUserCommDao.deleteByUserId(id);
		commActi.deleteByActiId(id);
		int rows = communityDao.deleteComm(id);
		if (rows == 0)
			throw new ServiceException("社区可能已经不存在");
		return rows;
	}

	/** 加入社区 */
	@Override
	public int addOK(PetcCommunityPO entity, Integer[] commId) {
		// 1.参数校验
		if (entity == null)
			throw new ServiceException("保存对象不能为空");
		if (StringUtils.isEmpty(entity.getCommName()))
			throw new ServiceException("用户名不能为空");
		int rows = communityDao.addC(entity);
		petcUserCommDao.addC(entity.getId(), commId);
		return rows;
	}

	/** 退出社区 */
	@Override
	public void deleteObject(PetcCommunityPO entity, Integer commId) {

		if (entity == null)
			throw new ServiceException("不存在");
		communityDao.deleteComm(commId);
		communityDao.deleteObject(entity);
	}

	@Override
	public PetcUserPO getUserNameById(Integer id) {
		return communityDao.getUserById(id);
	}

	@Override
	public PetcCommDetailVO getCommDetailVO(Integer id) {
		// 1.根据id获取社区信息
		PetcCommunityPO po = communityDao.getCommunityById(id);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		po.setCreatedTimeStr(format.format(po.getCreatedTime()));
		po.setModifiedTimeStr(format.format(po.getModifiedTime()));
		// 2.根据创建者id获取创建者信息
		PetcUserPO createdUser = communityDao.getUserById(po.getCreatedUser());
		// 3.分析登录账号相对此社区的类型
		Integer myId = ShiroUtils.getUserId();
		Integer relationship = 0;
		if (myId.intValue() == createdUser.getId().intValue()) {
			relationship = 2;
		} else if (communityDao.getUserCommCount(id, myId) > 0) {
			relationship = 1;
		}
		PetcCommDetailVO vo = new PetcCommDetailVO();
		vo.setId(id);
		vo.setNickname(createdUser.getNickname());
		vo.setRelationship(relationship);
		vo.setPo(po);
		return vo;
	}

	@Override
	public void doCreateComm(String commName, String position) {
		// 1.插入社区表
		if (commName==null || commName=="")
			throw new ServiceException("用户名不能为空");
		 List<PetcCommunityPO> list = communityDao.findPetcCommunityByName(commName);
		if(list!=null)
			throw new  ServiceException("用户名已存在");
		PetcCommunityPO po = new PetcCommunityPO();
		po.setCommName(commName);
		po.setPosition(position);
		po.setValid(1);
		po.setCreatedTime(new Date());
		po.setModifiedTime(po.getCreatedTime());
		Integer userId = ShiroUtils.getUserId();
		po.setCreatedUser(userId);
		po.setModifiedUser(po.getCreatedUser());
		communityDao.insertCommPO(po);
		// 2.查出id
		Integer commId = communityDao.findCommIdByPO(po);
		// 3.向用户、社区关系表中插入一条关联数据
		communityDao.insertRelationshipByUserComm(userId, commId);
	}

}
