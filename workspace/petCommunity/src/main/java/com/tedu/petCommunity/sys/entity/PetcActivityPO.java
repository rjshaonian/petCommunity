/**
 * 
 */
package com.tedu.petCommunity.sys.entity;

import java.util.Date;

import lombok.Data;

/**
 * @author 彬彬 上午8:45:03
 */
@Data

public class PetcActivityPO {
	// 主键id
	private Integer id;
	// 活动名
	private String actiName;
	/// 常用定位
	private String position;
	// 是否启用0:未启用;1:启用
	private Integer valid;
	// 宠物类型
	private String actiType;/* integer */
	// 开始时间
	// @DateTimeFormat(pattern = "yyyy-MM-dd")
	// 开始时间
	private Date startTime;
	private String startTimeStr;
	// 结束时间
	// @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endTime;
	private String endTimeStr;
	// 创建时间
	private Date createdTime;
	private String createdTimeStr;
	// 修改时间
	private Date modifiedTime;/* i */
	private String modifiedTimeStr;
	// 创建者id
	private Integer createdUser;
	// 修改者id
	private Integer modifiedUser;

}
