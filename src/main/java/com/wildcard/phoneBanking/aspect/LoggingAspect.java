package com.wildcard.phoneBanking.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	private static final Logger slf4jLogger = LoggerFactory.getLogger(LoggingAspect.class);

	@Before("execution(* com.wildcard.phoneBanking.svc.impl.UserManagerImpl.getBalance(..) )")
	public void logBeforeGetBalance() {
		slf4jLogger.info("ASPECT:: Inside logBeforeExistUser method before calling getBalance of UserManagerImpl");
	}

	@After("execution(public void com.wildcard.phoneBanking.svc.impl.TransferManagerImpl.sendMoney(..))")
	public void logAftersendMoney() {
		slf4jLogger.info("ASPECT:: Inside logAftersendMoney method after calling sendMoney of TransferManagerImpl");
	}

	@Before("allGetters()")
	public void beforeAllGetters() {
		slf4jLogger.info("ASPECT:: Inside beforeAllGetters method before calling Getters");
	}

	@Pointcut("execution(* get*())")
	public void allGetters() {
	}

	@Before("allMethodsUserManagermpl()")
	public void beforeAllMethodsUser(JoinPoint joinPoint) {
		slf4jLogger.info("ASPECT:: Inside beforeAllMethodsUser method");
		slf4jLogger.info("ASPECT:: JointPoint" + joinPoint.toString());
	}

	@Pointcut("within(com.wildcard.phoneBanking.svc.impl.UserManagerImpl)")
	public void allMethodsUserManagermpl() {
	}

	@Before("args(userName)")
	public void stringArgumentsMethods(String userName) {
		slf4jLogger.info("ASPECT:: Inside stringArgumentsMethods");
		slf4jLogger.info("ASPECT:: args :: The name of user is : " + userName);
	}

	@AfterReturning(pointcut = "args(userName)", returning = "returning")
	public void stringAfterArgumentsMethods(String userName, String returning) {
		slf4jLogger.info("ASPECT:: Inside stringAfterArgumentsMethods");
		slf4jLogger.info("ASPECT:: After Returning args :: The name of user is : " + userName
				+ "and the returning String is :" + returning);
	}

	@AfterThrowing(pointcut = "args(userName)", throwing = "exception")
	public void stringAfterException(String userName, Exception exception) {
		slf4jLogger.info("ASPECT:: Inside stringAfterException");
		slf4jLogger.info("ASPECT:: args :: Exception has been thrown" + exception);
	}

	@Around("allMethodsUserManagermpl()")
	public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		slf4jLogger.info("ASPECT:: Inside aroundAdvice");
		Object returningValue = null;
		try {
			slf4jLogger.info("ASPECT:: Before aroundAdvice");
			returningValue = proceedingJoinPoint.proceed();
			slf4jLogger.info("ASPECT:: After aroundAdvice");
		} catch (Throwable e) {
			slf4jLogger.info("ASPECT:: After Throwing");
		}
		slf4jLogger.info("ASPECT:: returning value is "+returningValue);
		return returningValue;
	}
}
