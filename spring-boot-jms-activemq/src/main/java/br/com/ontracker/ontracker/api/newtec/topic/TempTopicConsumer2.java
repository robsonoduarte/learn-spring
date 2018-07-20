package br.com.ontracker.ontracker.api.newtec.topic;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import br.com.ontracker.ontracker.api.newtec.domain.Temp;

@Component
public class TempTopicConsumer2 {

	
	
	
	@JmsListener(destination="temp.topic", containerFactory="topicListenerFactory")
	public void receive(Temp temp) {
		System.out.println("Recebendo no consumer 2 => " + temp);
	}
}
