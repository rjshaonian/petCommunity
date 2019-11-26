package com.tedu.petCommunity.sys.vo;

import java.io.Serializable;

import com.tedu.petCommunity.sys.entity.PetcActivityPO;

import lombok.Data;

/**
 * @author 阳昊 2019年11月27日 上午3:43:26
 */
@Data
public class PetcActiDetailVO implements Serializable {
	private static final long serialVersionUID = -7590384053583425816L;
	private Integer id;
	// 0：未加入；1：已加入；2：创建者
	private Integer relationship;
	private String nickname;
	private PetcActivityPO po;
}
