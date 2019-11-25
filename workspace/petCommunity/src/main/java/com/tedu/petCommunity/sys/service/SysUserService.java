package com.tedu.petCommunity.sys.service;

import com.tedu.petCommunity.sys.entity.SysUser;

public interface SysUserService {
	// List<SysUserMenuVo> findUserMenus();
	static boolean isExists(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	static int saveObject(String username, String password) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @param username
	 * @return
	 */
	static SysUser findByUserName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
