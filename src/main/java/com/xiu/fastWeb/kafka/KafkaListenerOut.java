package com.xiu.fastWeb.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerOut {

	@KafkaListener(topics = {"test"})
    public void listen(ConsumerRecord<?, ?> record) {
        System.err.println("kafka的key: " + record.key());
        System.err.println("kafka的value: " + record.value().toString());
    }
}
