/**
 * 
 */
package com.tedu.petCommunity.sys.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

/**
 * @author 彬彬 上午9:12:27
 */
@Data
@ToString
public class PetcCommunityPO implements Serializable {
	private static final long serialVersionUID = 8924387722922123121L;
	// 主键id
	private Integer id;
	// 社区名
	private String commName;
	// 位置
	private String position;
	// 是否启用0:未启用;1:启用
	private Integer valid;
	// 创建时间
	private Date createTime;
	// 修改时间
	private Date modifiedTime;
	// 创建者id
	private Integer createUser;
	// 修改者id
	private Integer modifiedUser;

}
