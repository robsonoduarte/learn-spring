package br.com.mystudies.springboot.jms.activemq.queue;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import br.com.mystudies.springboot.jms.activemq.domain.Message;

@Component
public class ConsumerQueue1 {

	@JmsListener(destination="temp.queue", containerFactory="queueListenerFactory")
	public void consume(Message message){
		System.out.println("Consumer Queue 1 ->" + message);
	}

}
