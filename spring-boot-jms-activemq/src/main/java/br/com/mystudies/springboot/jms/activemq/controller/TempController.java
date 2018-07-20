package br.com.mystudies.springboot.jms.activemq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mystudies.springboot.jms.activemq.domain.Temp;
import br.com.mystudies.springboot.jms.activemq.queue.TempQueueSender;
import br.com.mystudies.springboot.jms.activemq.topic.TempTopicSender;

@RestController
public class TempController {

	@Autowired
	private TempTopicSender ts;
	
	@Autowired
	private TempQueueSender qs;
	
	@GetMapping("topic")
	public void topic() {
		Temp temp = new Temp();
		temp.setName("temp");
		ts.send(temp);
	}
	
	
	@GetMapping("queue")
	public void queue() {
		Temp temp = new Temp();
		temp.setName("temp");
		qs.send(temp);
	}
}
