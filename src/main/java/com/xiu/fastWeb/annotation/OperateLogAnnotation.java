package com.xiu.fastWeb.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//自定义注解
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateLogAnnotation {

	public enum Type {ADD, DELETE, MODIFY, BLANK}

	Type value() default Type.BLANK;
}
