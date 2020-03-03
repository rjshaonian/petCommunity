package com.tedu.petCommunity.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.Jedis;

/**
 * @author 阳昊 2020年3月3日 下午10:32:12
 */
@Configuration // 标识配置类
public class RedisConfig {

	@Value("${redis.host}")
	private String host;
	@Value("${redis.port}")
	private Integer port;
	@Value("${redis.password}")
	private String password;

	@Bean
	public Jedis jedis() {
		Jedis jedis = new Jedis(host, port);
		jedis.auth(password);
		return jedis;
	}

}
