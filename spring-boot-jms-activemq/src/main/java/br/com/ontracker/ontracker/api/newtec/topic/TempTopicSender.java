package br.com.ontracker.ontracker.api.newtec.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import br.com.ontracker.ontracker.api.newtec.domain.Temp;

@Component
public class TempTopicSender {

	
	@Autowired
	private JmsTemplate jmsTemplateTopic;
	
	
	public void send(Temp temp) {
		jmsTemplateTopic.convertAndSend("temp.topic", temp);
	}
	
}
