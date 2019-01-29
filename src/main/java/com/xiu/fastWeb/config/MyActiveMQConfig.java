package com.xiu.fastWeb.config;

import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;

//https://segmentfault.com/a/1190000011190467
//https://www.cnblogs.com/gossip/p/5977489.html

@Configuration 
@PropertySource("classpath:activemq.properties")
public class MyActiveMQConfig {
	
	@Value("${spring.activemq.userName}")
	private String userName;
	@Value("${spring.activemq.passworld}")
	private String passWord;
	@Value("${spring.activemq.broker-url}")
	private String brokerUrl;
	
	
	@Bean
    public Queue queue() {
       return new ActiveMQQueue("sample.queue");
    }
	
	@Bean
    public Topic topic(){
        return new ActiveMQTopic("sample.topic");
    }

	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
	    return new ActiveMQConnectionFactory(userName, passWord, brokerUrl);
	}
	
	//queue模式
	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ActiveMQConnectionFactory connectionFactory){
	    DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
	    bean.setConnectionFactory(connectionFactory);
	    return bean;
	}
	
	//topic模式
	@Bean
	public JmsListenerContainerFactory<?>  jmsListenerContainerTopic(ActiveMQConnectionFactory connectionFactory){
	    DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
	    //设置为发布订阅方式, 默认情况下使用的生产消费者方式
	    bean.setPubSubDomain(true);
	    bean.setSubscriptionDurable(true);//持久化配置
	    bean.setConnectionFactory(connectionFactory);
	    bean.setClientId("myclient");//客户端id，唯一
	    return bean;
	}
	 
	@Bean
	public JmsMessagingTemplate jmsMessagingTemplate(ActiveMQConnectionFactory connectionFactory){
	    return new JmsMessagingTemplate(connectionFactory);
	}	
}
