/**
 * 
 */
package com.tedu.petCommunity.sys.entity;

import java.util.Date;

import lombok.Data;

/**
 * @author VictorHe 2019年11月24日 下午8:36:45
 */
@Data
public class PetcUserChatVO {
	// 主键id
	private Integer id;
	// 用户名
	private String username;
	// 关联社区id
	private Integer commId;
	// 内容
	private String content;
	// 是否启用0:未启用;1:启用
	private String valid;
	// 创建时间
	private Date createdTime;
	// 创建时间
	private String createdTimeStr;
	// 创建者id
	private String createdUser;
}
