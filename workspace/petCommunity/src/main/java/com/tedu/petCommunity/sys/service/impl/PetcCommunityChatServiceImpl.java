/**
 * 
 */
package com.tedu.petCommunity.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tedu.petCommunity.common.exception.ServiceException;
import com.tedu.petCommunity.sys.dao.PetcCommunityChatDao;
import com.tedu.petCommunity.sys.entity.PetcCommunityPO;
import com.tedu.petCommunity.sys.entity.PetcUserChatVO;
import com.tedu.petCommunity.sys.service.PetcCommChatService;
import com.tedu.petCommunity.sys.vo.PageObject;

/**
 * @author VictorHe 2019年11月24日 下午4:45:34
 */
@Service
public class PetcCommunityChatServiceImpl implements PetcCommChatService {
	@Autowired
	private PetcCommunityChatDao petcCommunityChatDao;

//	@Async
//	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public PageObject<PetcUserChatVO> findPageObjects(Integer commId, Integer PageCurrent) {
		if (PageCurrent == null || PageCurrent < 1) {
			PageCurrent = 1;
		}
		int rowCount = petcCommunityChatDao.getRowCount(commId);
		if (rowCount == 0) {
			throw new ServiceException("记录不存在");
		}
		int pageSize = 3;
		int startIndex = (PageCurrent - 1) * pageSize;
		List<PetcUserChatVO> records = petcCommunityChatDao.findPageObjects(commId, startIndex, pageSize);
		Integer pageCount = (rowCount - 1) / pageSize + 1;
		return new PageObject<PetcUserChatVO>(PageCurrent, pageSize, rowCount, pageCount, records);
	}

	@Override
	public PetcCommunityPO getCommById(Integer id) {
		return petcCommunityChatDao.getCommById(id);
	}

}
