package com.tedu.petCommunity.dailyreport;

import java.util.Date;

import lombok.Data;

/**
 * PO类，与数据库各列一一对应
 * 
 * @author 阳昊 下午1:30:38
 */
@Data
public class PetcDailyReportPO {
	private Integer id;
	private String name;
	private Date createdTime;
	private Date modifiedTime;
	private String modifiedFile;
	private String description;
}
