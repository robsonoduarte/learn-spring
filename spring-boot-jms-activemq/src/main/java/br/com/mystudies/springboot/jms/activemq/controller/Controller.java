package br.com.mystudies.springboot.jms.activemq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mystudies.springboot.jms.activemq.domain.Message;
import br.com.mystudies.springboot.jms.activemq.queue.SenderQueue;
import br.com.mystudies.springboot.jms.activemq.topic.SenderTopic;

@RestController
public class Controller {

	@Autowired
	private SenderTopic senderTopic;

	@Autowired
	private SenderQueue senderQueue;


	@GetMapping("topic")
	public void topic() {
		Message message = new Message();
		message.message = "Send message to Topic";
		senderTopic.send(message);
	}


	@GetMapping("queue")
	public void queue() {
		Message message = new Message();
		message.message = "Send message to Queue";
		senderQueue.send(message);
	}

}
