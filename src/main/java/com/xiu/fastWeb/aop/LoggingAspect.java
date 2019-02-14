package com.xiu.fastWeb.aop;


import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private Environment environment;
	
	@Pointcut("within(com.xiu.fastWeb.controller..*)")
	public void loggingAspect() {
		
	}
	
	@AfterThrowing(pointcut="loggingAspect()",throwing ="e")
	public void logAfterLogThrowing(JoinPoint joinPoint,Throwable e) {
		if(environment.acceptsProfiles("dev")) {
			log.error("Exception in {}.{}() with cause =\'{}\' and exception = \'{}\'",joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(), e.getCause()!= null ?e.getCause():"NULL",e.getMessage(),e);
		}else {
			log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
	                joinPoint.getSignature().getName(), e.getCause() != null? e.getCause() : "NULL");
		}
	}
	@Around("loggingAspect()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable{
		
		if(log.isInfoEnabled()) {
			log.info("Enter: {}.{}() with argument[s] ={}",joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(),Arrays.toString(joinPoint.getArgs()));
		}
		try {
			Object object = joinPoint.proceed();
			if (log.isInfoEnabled()) {
                log.info("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), object);
            }
            return object;
		} catch (Throwable e) {
			log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());

            throw e;
		}
	}
	
}
