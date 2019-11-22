package com.tedu.petCommunity.dailyreport.service;

import java.util.List;

import com.tedu.petCommunity.dailyreport.PetcDailyReportPO;
import com.tedu.petCommunity.dailyreport.vo.PetcDailyReportVO;

/**
 * @author 阳昊 上午11:45:55
 */
public interface PetcDailyReportService {

	/**
	 * 根据name、type查询数据
	 * 
	 * @param name 为空时，默认查询所有
	 * @param type 0：查询当天；1：查询全部
	 * @return
	 */
	List<PetcDailyReportVO> getDailyReports(String name, Integer type);

	/**
	 * 根据id删除数据
	 * 
	 * @param id
	 */
	void deleteDailyReport(Integer id);

	/**
	 * @param id
	 * @param name
	 * @param modifiedFiles
	 * @param description
	 */
	void setDailyReport(Integer id, String name, String modifiedFiles, String description);

	/**
	 * @param id
	 * @return
	 */
	PetcDailyReportPO queryDailyReportById(Integer id);

}
