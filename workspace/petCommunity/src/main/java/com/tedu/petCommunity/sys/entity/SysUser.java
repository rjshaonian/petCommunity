package com.tedu.petCommunity.sys.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class SysUser implements Serializable {
	private static final long serialVersionUID = -2084506667460906909L;
	private String salt;
	private String username;
	private String password;
}
