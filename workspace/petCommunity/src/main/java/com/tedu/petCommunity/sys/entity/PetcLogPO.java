package com.tedu.petCommunity.sys.entity;

import java.util.Date;

import lombok.Data;

/**
 * @author 阳昊 下午3:01:25
 */
@Data
public class PetcLogPO {
	// 主键
	private Integer id;
	// 操作用户id
	private Integer userId;
	// 操作用户id
	private String username;
	// 操作
	private String operation;
	// 方法
	private String method;
	// 参数
	private String params;
	// 时长
	private Long time;
	// ip地址
	private String ip;
	// 创建时间
	private Date createdTime;
}
