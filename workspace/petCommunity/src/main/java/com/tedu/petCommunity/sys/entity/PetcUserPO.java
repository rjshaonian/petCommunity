package com.tedu.petCommunity.sys.entity;

import java.util.Date;

import lombok.Data;

/**
 * @author 阳昊 下午7:30:50
 */
@Data
public class PetcUserPO {
	// 主键id
	private Integer id;
	// 登录用户名
	private String username;
	// 密码(已加密)
	private String password;
	// 密码加密盐值
	private String salt;
	// 昵称
	private String nickname;
	// 常用定位
	private String positioning;
	// 电子邮箱
	private String email;
	//验证码
	private Integer code;
	// 电话
	private String mobile;
	// 宠物类型
	private String petType;
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
