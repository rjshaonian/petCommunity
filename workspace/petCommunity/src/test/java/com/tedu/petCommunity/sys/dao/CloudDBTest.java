/**
 * 
 */
package com.tedu.petCommunity.sys.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 阳昊 下午1:51:13
 */
@SpringBootTest
public class CloudDBTest {

	@Autowired
	TestCloudDBDao testCloudDBDao;

	/**
	 * 测试云数据库连接
	 */
	@Test
	@RequestMapping("insertAll")
	public void insertTest() {
		testCloudDBDao.insertTest();
	}
}
