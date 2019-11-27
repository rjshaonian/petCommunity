package com.tedu.petCommunity.common.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tedu.petCommunity.common.util.IPUtils;
import com.tedu.petCommunity.common.util.ShiroUtils;
import com.tedu.petCommunity.sys.dao.PetcLogDao;
import com.tedu.petCommunity.sys.entity.PetcLogPO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 阳昊 2019年11月22日 下午3:11:14
 */
@Order(1)
@Aspect
@Component
@Slf4j
public class PetcLogAspect {
//	@Pointcut("@annotation(com.tedu.petCommunity.common.annotation.RequiredLog)")
	@Pointcut("bean(*ServiceImpl)")
	public void logPointCut() {
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		// 1.记录目标方法开始执行时间
		long t1 = System.currentTimeMillis();
		log.info("start time:" + t1);
		// 2.执行目标方法
		Object result = jp.proceed();// 假如有下一个切面先执行切面对象方法
		// 3.记录目标方法结束执行时间
		long t2 = System.currentTimeMillis();
		log.info("end time:" + t2);
		// 记录用户行为日志
		saveLog(jp, (t2 - t1));
		return result;
	}

	@Autowired
	private PetcLogDao logDao;

	// 获取用户行为日志信息,然后将日志写入到数据库
	private void saveLog(ProceedingJoinPoint joinPoint, long time) {
		// 1.获取日志信息
		// 获取目标方法对象
		Method targetMethod = getTargetMethod(joinPoint);
		// 获取目标方法名:目标类全名+方法名
		String classMethodName = getTargetMethodName(targetMethod);
		// 获取操作名
		String operation = getOperation(targetMethod);
		// String params = Arrays.toString(joinPoint.getArgs());
		// 获取方法执行时的实际参数
		String params = null;
		try {
			params = new ObjectMapper().writeValueAsString(joinPoint.getArgs());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		// 2.封装日志信息
		PetcLogPO logPO = new PetcLogPO(null, ShiroUtils.getUserId(), ShiroUtils.getUsername(), operation,
				classMethodName, params, time, IPUtils.getIpAddr(), new Date());
		// 3.持久化日志信息
		new Thread() {
			public void run() {
				logDao.insertObject(logPO);
			};
		}.start();
//		logService.saveObject(logPO);
	}

	private String getOperation(Method targetMethod) {
//		RequiredLog rLog = targetMethod.getAnnotation(RequiredLog.class);
//		return rLog.value();
		return targetMethod.getName();
	}

	private String getTargetMethodName(Method targetMethod) {
		return targetMethod.getDeclaringClass().getName() + "." + targetMethod.getName();
	}

	private Method getTargetMethod(ProceedingJoinPoint joinPoint) {
		Class<?> targetClass = joinPoint.getTarget().getClass();
		MethodSignature s = (MethodSignature) joinPoint.getSignature();// 方法签名
		Method targetMethod = null;
		try {
			targetMethod = targetClass.getMethod(s.getName(), s.getParameterTypes());
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return targetMethod;
	}
}
