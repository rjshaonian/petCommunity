package com.tedu.petCommunity.sys.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 阳昊 下午1:52:45
 */
@Mapper
public interface TestCloudDBDao {
	/**
	 * 向test表中插入一条数据
	 * 
	 * @return
	 */
	@Insert("insert into test value (null,'test')")
	int insertTest();
}
