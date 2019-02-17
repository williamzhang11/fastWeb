package com.xiu.fastWeb.aop;


import static org.hamcrest.CoreMatchers.nullValue;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.xiu.fastWeb.annotation.OperateLogAnnotation;


@Aspect
@Component
public class LogAnnotationAspect {

	@Pointcut("@annotation(com.xiu.fastWeb.annotation.OperateLogAnnotation)")
	public void logAnnotation(){
	}
	
	@Before("logAnnotation()")
	public void beforepointCut(JoinPoint joinPoint) {
		 MethodSignature sign =  (MethodSignature)joinPoint.getSignature();
		 OperateLogAnnotation operateLogAnnotation = sign.getMethod().getAnnotation(OperateLogAnnotation.class);
		 System.out.println("before:" +operateLogAnnotation.value());
	}
	
	@After("logAnnotation()")
	public void afterPointCut(JoinPoint joinPoint) {
		MethodSignature sign =  (MethodSignature)joinPoint.getSignature();
		OperateLogAnnotation operateLogAnnotation = sign.getMethod().getAnnotation(OperateLogAnnotation.class);
		System.out.println("after:" +operateLogAnnotation.value());
	}
	@Around("logAnnotation()")
	public Object aroundPointcut(ProceedingJoinPoint proceedingJoinPoint) {
		
		System.out.println("aroundBefore");
		Object object = null;
		try {
			object = proceedingJoinPoint.proceed();
			System.out.println("doing");
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
		System.out.println("aroundAfter");
		return object;
	}
	
	@AfterThrowing(pointcut="logAnnotation()",throwing="e")
	public void exceptionPointCut(Exception  e) {
		System.out.println("目标方法中抛出的异常:" + e);
	}
	
}
