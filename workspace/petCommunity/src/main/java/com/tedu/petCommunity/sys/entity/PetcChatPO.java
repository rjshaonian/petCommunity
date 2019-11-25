/**
 * 
 */
package com.tedu.petCommunity.sys.entity;

import java.util.Date;

import lombok.Data;

/**
 * @author 彬彬 上午8:55:42
 */
@Data
public class PetcChatPO {
	// 主键id
	private Integer id;
	// 关联社区id
	private Integer commId;
	// 内容
	private String content;
	// 是否启用0:未启用;1:启用
	private String valid;
	// 创建时间
	private Date createTime;
	// 创建者id
	private String createUser;

}
