package com.tedu.petCommunity.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.tedu.petCommunity.common.web.TimeAccessInterceptor;

/**
 * @author 阳昊 2019年11月23日 下午3:34:40
 */
@Configuration
public class SpringWebConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TimeAccessInterceptor()).addPathPatterns("/user/doLogin");
	}
}