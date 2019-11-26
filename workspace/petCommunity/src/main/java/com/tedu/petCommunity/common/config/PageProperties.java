package com.tedu.petCommunity.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "db.page")
public class PageProperties {
	private Integer pageSize = 3;
}
