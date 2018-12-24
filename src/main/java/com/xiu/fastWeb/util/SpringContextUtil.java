package com.xiu.fastWeb.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext){
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    //通过名字获取上下文中的bean
    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }

    //通过类型获取上下文中的bean
    public static <T> Object getBean(Class<T> requiredType){
        return applicationContext.getBean(requiredType);
    }

}

