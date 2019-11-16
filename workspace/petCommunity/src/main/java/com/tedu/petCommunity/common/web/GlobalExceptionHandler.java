package com.tedu.petCommunity.common.web;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tedu.petCommunity.common.vo.JsonResult;

/** 全局异常处理类 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	// JDK中的自带的日志API
	@ExceptionHandler(RuntimeException.class)
	public JsonResult doHandleRuntimeException(RuntimeException e) {
		e.printStackTrace();// 也可以写日志
		return new JsonResult(e);// 封装异常信息
	}

}