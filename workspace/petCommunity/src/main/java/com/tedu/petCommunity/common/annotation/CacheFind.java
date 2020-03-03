package com.tedu.petCommunity.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 阳昊 2020年3月3日 下午10:54:19
 */
@Target(ElementType.METHOD) // 标识注解对方法生效
@Retention(RetentionPolicy.RUNTIME) // 标识当前注解的生命周期
public @interface CacheFind {
	/**
	 * 注解中配置的内容
	 * 
	 * @1.简单 用户使用起来简单
	 * @2.实用
	 * @要求:key不同的业务一定不能重复
	 * @规则:key值默认为""，如果用户添加了key，则使用用户的，如果用户没有添加key，生成策略:包名.类名.方法名::第一个参数
	 * @添加时间:如果不为0，则需要数据添加过期时间setex
	 */
	String key() default "";

	int seconds() default 0;
}
