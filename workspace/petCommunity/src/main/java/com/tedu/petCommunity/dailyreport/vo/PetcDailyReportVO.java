package com.tedu.petCommunity.dailyreport.vo;

import java.util.List;

import lombok.Data;

/**
 * VO类，用于组织返回给前端的数据
 * 
 * @author 阳昊 下午1:17:54
 */
@Data
public class PetcDailyReportVO {
	private Integer id;
	private String name;
	// 前端界面不需要的数据
	// private Date createdTime;
	// private Date modifiedTime;
	// private String modifiedFile;
	private String description;
	/*************************** 为前端页面组织的相关参数 ***************************/
	// 将createdTime、modifiedTime转换成符合习惯的可读字符串
	private String createdTimeStr;
	private String modifiedTimeStr;
	// 将modifiedFile以“;”分割后的字段
	private List<String> modifiedFiles;
}
