package com.tedu.petCommunity.sys.service;

import java.util.List;

import com.tedu.petCommunity.common.vo.PageObject;
import com.tedu.petCommunity.sys.entity.PetcActivityPO;
import com.tedu.petCommunity.sys.entity.PetcUserPO;
import com.tedu.petCommunity.sys.vo.PetcActiDetailVO;

public interface PetcActivityService {
	PageObject<PetcActivityPO> findPageObjects(String acti_name, Integer pageCurrent);

	PetcActivityPO findActivityById(Integer id);

	int save(PetcActivityPO entity);

	PageObject<PetcActivityPO> findActivitys(Integer userId, Integer pageCurrent);

	int killActivity(Integer id);

	int update(PetcActivityPO entity);

	/**
	 * @param actiName
	 * @return
	 */
	List<PetcActivityPO> loadActi(String actiName);

	/**
	 * @param commName
	 * @param position
	 * @param actiType
	 * @param datetimeStart
	 * @param datetimeEnd
	 */
	void doCreateActi(String actiName, String position, String actiType, String datetimeStart, String datetimeEnd);

	/**
	 * @param id
	 * @return
	 */
	PetcActiDetailVO getActiDetailVO(Integer id);

	/**
	 * @param actiId
	 */
	void doJoin(Integer actiId);

	/**
	 * @param actiId
	 */
	void doExit(Integer actiId);

	/**
	 * @param actiId
	 */
	void doDisband(Integer actiId);

	/**
	 * @param actiId
	 * @param actiName
	 * @param position
	 * @param datetimeStart
	 * @param datetimeEnd
	 */
	void doModify(Integer actiId, String actiName, String position, String datetimeStart, String datetimeEnd);

	/**
	 * @param actiId
	 * @return
	 */
	List<PetcUserPO> getUsersByActiId(Integer actiId);

}
