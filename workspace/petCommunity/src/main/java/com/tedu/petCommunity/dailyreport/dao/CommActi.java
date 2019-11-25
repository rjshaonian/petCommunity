package com.tedu.petCommunity.dailyreport.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommActi {
	@Delete("delete from comm_acti where acti_id=#{actiId}")
	int deleteByActiId(Integer actiId);

}
