package com.xiu.fastWeb.activemq;


import javax.jms.Queue;
import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
	@Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
	
	@Autowired
	private Queue queue;
	
	@Autowired
    private Topic topic;
	
	//@Scheduled(fixedDelay=30000)
	public void sendMsg() {
		this.jmsMessagingTemplate.convertAndSend(this.queue, "hello world queue");
		 this.jmsMessagingTemplate.convertAndSend(this.topic, "hello world topic");
    }
}
