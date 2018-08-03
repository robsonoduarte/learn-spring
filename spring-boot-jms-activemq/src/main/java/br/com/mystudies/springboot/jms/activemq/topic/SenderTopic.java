package br.com.mystudies.springboot.jms.activemq.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import br.com.mystudies.springboot.jms.activemq.domain.Message;

@Component
public class SenderTopic {

	
	@Autowired
	private JmsTemplate jmsTemplateTopic;
	
	
	public void send(Message message) {
		jmsTemplateTopic.convertAndSend("temp.topic", message);
	}
	
}
