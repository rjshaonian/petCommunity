package com.tedu.petCommunity.common.config;

import java.util.LinkedHashMap;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 阳昊 2019年11月23日 上午8:41:32
 */
@Configuration
public class SpringShiroConfig {
	@Bean
	public SecurityManager securityManager(Realm realm) {
		DefaultWebSecurityManager sManager = new DefaultWebSecurityManager();
		sManager.setRealm(realm);
		return sManager;
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactory(SecurityManager securityManager) {
		ShiroFilterFactoryBean sfBean = new ShiroFilterFactoryBean();
		sfBean.setSecurityManager(securityManager);
		sfBean.setLoginUrl("/login");
		// 定义map指定请求过滤规则(哪些资源允许匿名访问,哪些必须认证访问)
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		// 静态资源允许匿名访问:"anon"
		map.put("/bower_components/**", "anon");
		map.put("/build/**", "anon");
		map.put("/dist/**", "anon");
		map.put("/plugins/**", "anon");
		map.put("/login", "anon");
		map.put("/logout", "logout");
		// 除了匿名访问的资源,其它都要认证("authc")后访问
		map.put("/**", "anon");
		sfBean.setFilterChainDefinitionMap(map);
		return sfBean;
	}

}
