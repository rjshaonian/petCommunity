package com.tedu.petCommunity.dailyreport.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import lombok.Data;

@Data
public class UserCommVo implements Serializable {
	private static final long serialVersionUID = 5226878434133183624L;

	private Integer commName;

	private String position;

	private Date modifiedTime;

	private Integer modifiedUser;

	private User user;

}
