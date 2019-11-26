package com.tedu.petCommunity.sys.vo;

import com.tedu.petCommunity.sys.entity.PetcUserPO;

import lombok.Data;

/**
 * @author 阳昊 2019年11月27日 上午6:13:27
 */
@Data
public class PetcPersonalVO {
	private PetcUserPO user;
	private Integer relationship;
}
