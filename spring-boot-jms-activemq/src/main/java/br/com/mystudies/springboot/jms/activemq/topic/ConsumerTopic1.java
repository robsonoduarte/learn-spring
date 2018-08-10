package br.com.mystudies.springboot.jms.activemq.topic;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import br.com.mystudies.springboot.jms.activemq.domain.Message;

@Component
public class ConsumerTopic1 {

	@JmsListener(destination="topic", containerFactory="topicListenerFactory")
	public void receive(Message mesasge) {
		System.out.println("Consumer Topic 1 => " + mesasge);
	}
}
