package com.tedu.petCommunity.dailyreport.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tedu.petCommunity.dailyreport.PetcDailyReportPO;

/**
 * @author 阳昊 下午1:40:01
 */
@Mapper
public interface PetcDailyReportDao {

	/**
	 * 根据name、type查询数据
	 * 
	 * @param name 为空时，默认查询所有
	 * @param type 0：查询当天；1：查询全部
	 * @return
	 */
	List<PetcDailyReportPO> queryDailyReports(@Param("name") String name, @Param("type") Integer type);

	/**
	 * 根据id删除数据
	 * 
	 * @param id
	 */
	@Delete("delete from daily_report where id=#{id}")
	int deleteDailyReport(@Param("id") Integer id);

	/**
	 * @param name
	 * @param date
	 * @param modifiedFiles
	 * @param description
	 */
	@Insert("insert into daily_report  values (null,#{name},#{date},#{date},#{modifiedFiles},#{description})")
	int insertDailyReport(String name, Date date, String modifiedFiles, String description);

	/**
	 * @param id
	 * @return
	 */
	@Select("select * from daily_report where id=#{id}")
	PetcDailyReportPO queryDailyReportById(Integer id);

	/**
	 * @param id
	 * @param name
	 * @param date
	 * @param modifiedFiles
	 * @param description
	 */
	@Update(" update daily_report set name=#{name}, modified_time=#{date}, modified_file=#{modifiedFiles}, description=#{description} where id=#{id}")
	int upDateDailyReport(Integer id, String name, Date date, String modifiedFiles, String description);

}
