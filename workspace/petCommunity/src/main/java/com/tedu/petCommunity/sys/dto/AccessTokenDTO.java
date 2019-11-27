package com.tedu.petCommunity.sys.dto;

import lombok.Data;

/**
 * @author 阳昊 2019年11月27日 下午3:04:58
 */
@Data
public class AccessTokenDTO {
	private String client_id;
	private String client_secret;
	private String code;
	private String redirect_uri;
	private String state;
}
