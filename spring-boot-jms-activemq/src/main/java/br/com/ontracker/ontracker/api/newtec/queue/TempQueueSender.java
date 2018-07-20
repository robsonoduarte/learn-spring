package br.com.ontracker.ontracker.api.newtec.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import br.com.ontracker.ontracker.api.newtec.domain.Temp;

@Component
public class TempQueueSender {

	
	@Autowired
	private JmsTemplate jmsTemplateQueue;
	
	public void send(Temp temp) {
		jmsTemplateQueue.convertAndSend("temp.queue", temp);
	}
	
}
