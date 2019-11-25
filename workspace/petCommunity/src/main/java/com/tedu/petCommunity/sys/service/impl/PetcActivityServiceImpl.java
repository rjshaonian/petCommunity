package com.tedu.petCommunity.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tedu.petCommunity.common.exception.ServiceException;
import com.tedu.petCommunity.dailyreport.vo.PageObject;
import com.tedu.petCommunity.sys.dao.PetcActivityDao;
import com.tedu.petCommunity.sys.entity.PetcActivityPO;
import com.tedu.petCommunity.sys.service.PetcActivityService;

@Service
public class PetcActivityServiceImpl implements PetcActivityService {
	
	@Autowired
	private PetcActivityDao activityDao;

	@Override
	public PageObject<PetcActivityPO> findActivitys(Integer userId, Integer pageCurrent) {
		
		  if(pageCurrent==null||pageCurrent<1)
		  throw new IllegalArgumentException("当前页码不正确");
		  int rowCount=activityDao.getRowCount(userId);
		  if(rowCount==0)
		  throw new ServiceException("系统没有查到对应活动的记录");
		  int pageSize=3;
		  int startIndex=(pageCurrent-1)*pageSize;
		  List<PetcActivityPO> records=
		  activityDao.findActivitys(userId, startIndex, pageSize);
		  PageObject<PetcActivityPO> pageObject=new PageObject<>();
		  pageObject.setPageCurrent(pageCurrent);
		  pageObject.setPageSize(pageSize);
		  pageObject.setRowCount(rowCount);
		  pageObject.setRecords(records);
		  pageObject.setPageCount((rowCount-1)/pageSize+1);
		  return pageObject;
	}

}
