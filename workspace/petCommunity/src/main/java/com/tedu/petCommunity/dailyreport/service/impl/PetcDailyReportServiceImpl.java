package com.tedu.petCommunity.dailyreport.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.tedu.petCommunity.common.exception.ServiceException;
import com.tedu.petCommunity.dailyreport.PetcDailyReportPO;
import com.tedu.petCommunity.dailyreport.dao.PetcDailyReportDao;
import com.tedu.petCommunity.dailyreport.service.PetcDailyReportService;
import com.tedu.petCommunity.dailyreport.vo.PetcDailyReportVO;

/**
 * @author 阳昊 上午11:46:45
 */
@Service
public class PetcDailyReportServiceImpl implements PetcDailyReportService {

	@Autowired
	PetcDailyReportDao petcDailyReportDao;

	@Override
	public List<PetcDailyReportVO> getDailyReports(String name, Integer type) {
		// 1.查询数据
		List<PetcDailyReportPO> dailyReportPOs = petcDailyReportDao.queryDailyReports(name, type);
		// 2.数据校验
		if (dailyReportPOs == null || dailyReportPOs.size() == 0) {
			return null;
		}
		// 3.组织返回参数
		List<PetcDailyReportVO> dailyReportVOs = new ArrayList<PetcDailyReportVO>();
		for (PetcDailyReportPO po : dailyReportPOs) {
			PetcDailyReportVO vo = new PetcDailyReportVO();
			vo.setId(po.getId());
			vo.setName(po.getName());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			vo.setCreatedTimeStr(format.format(po.getCreatedTime()));
			vo.setModifiedTimeStr(format.format(po.getModifiedTime()));
			String[] modifiedFiles = po.getModifiedFile().split(";");
			vo.setModifiedFiles(new ArrayList<String>(Arrays.asList(modifiedFiles)));
			vo.setDescription(po.getDescription());
			dailyReportVOs.add(vo);
		}
		return dailyReportVOs;
	}

	@Override
	public void deleteDailyReport(Integer id) {
		// 1.校验参数
		if (id == null || id == 0) {
			throw new ServiceException("请选择正确的数据");
		}
		// 2.删除数据
		int rows = petcDailyReportDao.deleteDailyReport(id);
		// 3.判断结果
		if (rows <= 0) {
			throw new ServiceException("数据可能已经不存在");
		}
	}

	@Override
	public void setDailyReport(Integer id, String name, String modifiedFiles, String description) {
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(modifiedFiles) || StringUtils.isEmpty(description)) {
			throw new ServiceException("【姓名】、【修改文件】、【工作描述】不可为空");
		}
		Date date = new Date();
		if (id == null || id == 0) {
			petcDailyReportDao.insertDailyReport(name, date, modifiedFiles, description);
		} else {
			petcDailyReportDao.upDateDailyReport(id, name, date, modifiedFiles, description);
		}
	}

	@Override
	public PetcDailyReportPO queryDailyReportById(Integer id) {
		// 1.校验参数
		if (id == null || id == 0) {
			throw new ServiceException("请选择正确的数据");
		}
		// 2.查询数据
		return petcDailyReportDao.queryDailyReportById(id);
	}

}
