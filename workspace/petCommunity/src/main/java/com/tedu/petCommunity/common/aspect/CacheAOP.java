package com.tedu.petCommunity.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.tedu.petCommunity.common.annotation.CacheFind;
import com.tedu.petCommunity.common.util.ObjectMapperUtil;

import redis.clients.jedis.Jedis;

/**
 * @author 阳昊 2020年3月3日 下午10:56:07
 */
@Component // 万能注解 交给容器管理
@Aspect // 自定义切面
public class CacheAOP {

	@Autowired(required = false)
	private Jedis jedis;
//	@Autowired(required = false)
//	private JedisCluster jedis;

	/**
	 * 环绕通知的语法
	 * 
	 * @throws Throwable
	 * 
	 * @返回值类型:任意类型用obj包裹
	 * @参数说明:必须包含并且位置是第一个ProceedingJoinPoint
	 * @通知标识:1.@Around("切入点表达式")2.@Around(切入点())
	 */
	@Around("@annotation(cacheFind)")
	public Object around(ProceedingJoinPoint joinPoint, CacheFind cacheFind) throws Throwable {
		Long start = System.currentTimeMillis();
		String key = getKey(joinPoint, cacheFind);
		String value = null;
		try {// 防止redis未开
			value = jedis.get(key);
		} catch (Throwable e) {
			System.out.println("jedis获取失败");
			// e.printStackTrace();
		}
		if (StringUtils.isEmpty(value)) {
			// 用户第一次查询
			Object obj = joinPoint.proceed();
			int seconds = cacheFind.seconds();
			value = ObjectMapperUtil.toJSON(obj);
			try {// 防止redis未开
				if (seconds > 0) {
					jedis.setex(key, seconds, value);
				} else {
					jedis.set(key, value);
				}
			} catch (Throwable e) {
				System.out.println("jedis设置失败");
				// e.printStackTrace();
			}
			Long end = System.currentTimeMillis();
			System.out.println("数据库查询：" + (end - start) + "ms");
			return obj;
		}

		// 用户不是第一次查询
		Long end = System.currentTimeMillis();
		System.out.println("redis查询：" + (end - start) + "ms");
		Object obj = ObjectMapperUtil.toObject(value, Object.class);
		return obj;

	}

	/**
	 * 获取key数据
	 *
	 * @param joinPoint
	 * @param cacheFind
	 * @return
	 */
	private String getKey(ProceedingJoinPoint joinPoint, CacheFind cacheFind) {
		String key = cacheFind.key();
		if (StringUtils.isEmpty(key)) {
			String className = joinPoint.getSignature().getDeclaringTypeName();
			String methodName = joinPoint.getSignature().getName();
			Object firstArgs = joinPoint.getArgs()[0];
			return className + "." + methodName + "::" + firstArgs;
		}
		return key;
	}

}
