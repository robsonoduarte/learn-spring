package br.com.mystudies.springboot.jms.activemq.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import br.com.mystudies.springboot.jms.activemq.domain.Temp;

@Component
public class SenderQueue {

	
	@Autowired
	private JmsTemplate jmsTemplateQueue;
	
	public void send(Temp temp) {
		jmsTemplateQueue.convertAndSend("temp.queue", temp);
	}
	
}
