package com.xiu.fastWeb.aop;


import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.xiu.fastWeb.annotation.OperateLogAnnotation;


@Aspect
@Component
public class LogAnnotationAspect {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Pointcut("@annotation(com.xiu.fastWeb.annotation.OperateLogAnnotation)")
	public void logAnnotation(){
	}
	
	@Before("logAnnotation()")
	public void beforepointCut(JoinPoint joinPoint) {
		 MethodSignature sign =  (MethodSignature)joinPoint.getSignature();
		 OperateLogAnnotation operateLogAnnotation = sign.getMethod().getAnnotation(OperateLogAnnotation.class);
		 log.info("before:" +operateLogAnnotation.value());
		 
		 MethodSignature methodSignature = (MethodSignature) sign;
		 Object[] args = joinPoint.getArgs();
		 String[] parameterNames = methodSignature.getParameterNames();
         Method method = methodSignature.getMethod();
         System.out.println("---------------参数列表开始-------------------------");
         for (int i =0 ,len=parameterNames.length;i < len ;i++){
             System.out.println("参数名："+ parameterNames[i] + " = " +args[i]);
         }
         System.out.println("---------------参数列表结束-------------------------");
	}
	
	@After("logAnnotation()")
	public void afterPointCut(JoinPoint joinPoint) {
		MethodSignature sign =  (MethodSignature)joinPoint.getSignature();
		OperateLogAnnotation operateLogAnnotation = sign.getMethod().getAnnotation(OperateLogAnnotation.class);
		log.info("after:" +operateLogAnnotation.value());
	}
	@Around("logAnnotation()")
	public Object aroundPointcut(ProceedingJoinPoint proceedingJoinPoint) {
		
		log.info("aroundBefore");
		Object object = null;
		try {
			object = proceedingJoinPoint.proceed();
			log.info("doing");
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
		log.info("aroundAfter");
		return object;
	}
	
	@AfterThrowing(pointcut="logAnnotation()",throwing="e")
	public void exceptionPointCut(Exception  e) {
		log.info("目标方法中抛出的异常:" + e);
	}
	
}
