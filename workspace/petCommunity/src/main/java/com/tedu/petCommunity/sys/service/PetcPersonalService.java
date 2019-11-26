package com.tedu.petCommunity.sys.service;

import com.tedu.petCommunity.sys.entity.PetcUserPO;

public interface PetcPersonalService {

	void doModifyPwd(String oldPwd, String newPwd);

	void doModifyInfo(PetcUserPO po);

}
