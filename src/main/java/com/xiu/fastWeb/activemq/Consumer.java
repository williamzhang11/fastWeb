package com.xiu.fastWeb.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

//需要mq消费者，打开改注释即可
@Component
public class Consumer {

	//@JmsListener(destination = "sample.topic",containerFactory="jmsListenerContainerTopic")
    public void receiveTopic(String text){
        System.out.println("Topic get1:"+text);
    }
    //@JmsListener(destination = "sample.topic",containerFactory="jmsListenerContainerTopic")
    public void receiveTopic2(String text){
        System.out.println("Topic get1:"+text);
    }
    //@JmsListener(destination = "sample.queue",containerFactory="jmsListenerContainerQueue")
    public void reviceQueue(String text){
        System.out.println("Queue get:"+text);
    }
	
}
