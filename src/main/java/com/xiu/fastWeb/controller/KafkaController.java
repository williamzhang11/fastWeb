package com.xiu.fastWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@RequestMapping("/send")
	public String sendKafka(@RequestParam String msg) {
		System.err.println("msg="+msg);
		kafkaTemplate.send("test", msg);
		System.err.println("send ok");
		
		return "ok";
	}
	
}
