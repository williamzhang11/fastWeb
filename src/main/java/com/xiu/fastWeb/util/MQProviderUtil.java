package com.xiu.fastWeb.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MQProviderUtil {

	private JmsTemplate jmsTemplate;
	
	public void send2Queue(String domainName,final Object message) {
		
		if(jmsTemplate.isPubSubDomain()) {
			jmsTemplate.setPubSubDomain(false);
		}
		if(StringUtils.isEmpty(domainName)){
			jmsTemplate.convertAndSend(message);
		}else {
			jmsTemplate.convertAndSend(domainName, message);
			
		}
	}
	
	public void send2Topic(String domainName, final Object message) {
		
		if(!jmsTemplate.isPubSubDomain()) {
			jmsTemplate.setPubSubDomain(true);
		}
		if(StringUtils.isEmpty(domainName)){
			jmsTemplate.convertAndSend(message);
		}else {
			jmsTemplate.convertAndSend(domainName, message);
			
		}
		
	}
}
