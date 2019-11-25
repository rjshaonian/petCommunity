/**
 * 
 */
package com.tedu.petCommunity.sys.vo;

import java.io.Serializable;

import com.tedu.petCommunity.sys.entity.PetcCommunityPO;

import lombok.Data;

/**
 * @author VictorHe 2019年11月23日 下午4:28:16
 */
@Data
public class PetcCommDetailVO implements Serializable {
	private static final long serialVersionUID = -1698360029083062424L;
	private Integer id;
	// 0：未加入；1：已加入；2：创建者
	private Integer relationship;
	private String nickname;
	private PetcCommunityPO po;
//	// 主键id
//	private Integer id;
//	// 社区名
//	private String commName;
//	// 位置
//	private String position;
//	// 是否启用0:未启用;1:启用
//	private Integer valid;
//	// 创建时间
//	private Date createTime;
//	// 修改时间
//	private Date modifiedTime;
//	// 创建者id
//	private Integer createUser;
//	// 修改者id
//	private Integer modifiedUser;
}
