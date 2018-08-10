package br.com.mystudies.springboot.jms.activemq.topic;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import br.com.mystudies.springboot.jms.activemq.domain.Message;

@Component
public class ConsumerTopic2 {

	@JmsListener(destination="topic", containerFactory="topicListenerFactory")
	public void receive(Message message) {
		System.out.println("Consumer Topic 2 => " + message);
	}
}
