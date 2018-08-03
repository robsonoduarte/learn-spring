package br.com.mystudies.springboot.jms.activemq.queue;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import br.com.mystudies.springboot.jms.activemq.domain.Message;

@Component
public class ConsumerQueue1 {

	
	@JmsListener(destination="temp.queue", containerFactory="queueListenerFactory")
	public void consume(Message temp){
		System.out.println("Queue Consumer1 ->" + temp);
	}
	
	
}
