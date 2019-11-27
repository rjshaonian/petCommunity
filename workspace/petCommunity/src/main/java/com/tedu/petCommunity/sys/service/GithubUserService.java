package com.tedu.petCommunity.sys.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 阳昊 2019年11月27日 下午3:21:21
 */
public interface GithubUserService {

	/**
	 * @param code
	 * @param state
	 * @param request
	 * @param response
	 */
	public void callback(String code, String state, HttpServletRequest request, HttpServletResponse response);

}
