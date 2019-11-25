/**
 * 
 */
package com.tedu.petCommunity.sys.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tedu.petCommunity.common.util.ShiroUtils;
import com.tedu.petCommunity.sys.dao.PetcChatDao;
import com.tedu.petCommunity.sys.entity.PetcChatPO;
import com.tedu.petCommunity.sys.entity.PetcCommunityPO;
import com.tedu.petCommunity.sys.entity.PetcUserChatVO;
import com.tedu.petCommunity.sys.service.PetcChatService;
import com.tedu.petCommunity.sys.vo.PageObject;

/**
 * @author VictorHe 2019年11月24日 下午4:45:34
 */
@Service
public class PetcChatServiceImpl implements PetcChatService {
	@Autowired
	private PetcChatDao chatDao;

//	@Async
//	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public PageObject<PetcUserChatVO> findPageObjects(Integer commId, Integer PageCurrent) {
		if (PageCurrent == null || PageCurrent < 1) {
			PageCurrent = 1;
		}
		int rowCount = chatDao.getRowCount(commId);
		if (rowCount == 0) {
			return new PageObject<PetcUserChatVO>();
		}
		int pageSize = 3;
		int startIndex = (PageCurrent - 1) * pageSize;
		List<PetcUserChatVO> records = chatDao.findPageObjects(commId, startIndex, pageSize);
		Integer pageCount = (rowCount - 1) / pageSize + 1;
		return new PageObject<PetcUserChatVO>(PageCurrent, pageSize, rowCount, pageCount, records);
	}

	@Override
	public PetcCommunityPO getCommById(Integer id) {
		return chatDao.getCommById(id);
	}

	@Override
	public List<PetcUserChatVO> getContentByCommId(String commId) {
		List<PetcUserChatVO> userChatVOs = chatDao.findUserChatByCommId(commId);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (PetcUserChatVO vo : userChatVOs) {
			vo.setCreatedTimeStr(format.format(vo.getCreatedTime()));
		}
		return userChatVOs;
	}

	@Override
	public void insertChatMessage(Integer commId, String chatMessage) {
		PetcChatPO po = new PetcChatPO();
		po.setCommId(commId);
		po.setContent(chatMessage);
		po.setValid(1);
		po.setCreatedTime(new Date());
		po.setCreatedUser(ShiroUtils.getUserId());
		chatDao.insertChatMessage(po);
	}

}
