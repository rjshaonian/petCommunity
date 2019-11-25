package com.tedu.petCommunity.sys.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tedu.petCommunity.common.exception.ServiceException;
import com.tedu.petCommunity.dailyreport.vo.PageObject;
import com.tedu.petCommunity.sys.dao.PetcCommunityDao;
import com.tedu.petCommunity.sys.entity.PetcCommunityPO;
import com.tedu.petCommunity.sys.service.PetcCommunityService;

@Service
public class PetcCommunityServiceImpl implements PetcCommunityService {
	
	@Autowired
	private PetcCommunityDao communityDao;


	@Override
	public PageObject<PetcCommunityPO> findCommunitys(Integer userId, Integer pageCurrent) {

		  if(pageCurrent==null||pageCurrent<1)
		  throw new IllegalArgumentException("当前页码不正确");
		  int rowCount=communityDao.getRowCount(userId);
		  if(rowCount==0)
		  throw new ServiceException("系统没有查到相关社区的记录");
		  int pageSize=2;
		  int startIndex=(pageCurrent-1)*pageSize;
		  List<PetcCommunityPO> records=
		  communityDao.findCommunitys(userId, startIndex, pageSize);
		  PageObject<PetcCommunityPO> pageObject=new PageObject<>();
		  pageObject.setPageCurrent(pageCurrent);
		  pageObject.setPageSize(pageSize);
		  pageObject.setRowCount(rowCount);
		  pageObject.setRecords(records);
		  pageObject.setPageCount((rowCount-1)/pageSize+1);
		  
		  return pageObject;
	}

}
