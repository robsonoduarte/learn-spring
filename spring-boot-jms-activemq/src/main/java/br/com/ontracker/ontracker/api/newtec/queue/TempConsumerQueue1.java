package br.com.ontracker.ontracker.api.newtec.queue;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import br.com.ontracker.ontracker.api.newtec.domain.Temp;

@Component
public class TempConsumerQueue1 {

	
	@JmsListener(destination="temp.queue", containerFactory="queueListenerFactory")
	public void consume(Temp temp){
		System.out.println("Queue Consumer1 ->" + temp);
	}
	
	
}
