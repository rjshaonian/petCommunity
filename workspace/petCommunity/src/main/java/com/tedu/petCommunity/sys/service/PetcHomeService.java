package com.tedu.petCommunity.sys.service;

import com.tedu.petCommunity.sys.vo.PetcHomeContentVO;

/**
 * @author 阳昊 2019年11月26日 上午2:14:57
 */
public interface PetcHomeService {

	/**
	 * @param type
	 * @return
	 */
	PetcHomeContentVO<?> getContentByType(String type);

}
