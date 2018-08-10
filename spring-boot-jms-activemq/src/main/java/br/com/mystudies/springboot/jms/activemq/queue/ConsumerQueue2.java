package br.com.mystudies.springboot.jms.activemq.queue;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import br.com.mystudies.springboot.jms.activemq.domain.Message;

@Component
public class ConsumerQueue2 {

	@JmsListener(destination="queue", containerFactory="queueListenerFactory")
	public void consume(Message message){
		System.out.println("Consumer Queue 2 ->" + message);
	}

}
