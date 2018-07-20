package br.com.ontracker.ontracker.api.newtec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ontracker.ontracker.api.newtec.domain.Temp;
import br.com.ontracker.ontracker.api.newtec.queue.TempQueueSender;
import br.com.ontracker.ontracker.api.newtec.topic.TempTopicSender;

@RestController
public class TempController {

	@Autowired
	private TempTopicSender ts;
	
	@Autowired
	private TempQueueSender qs;
	
	@GetMapping("topic")
	public void topic() {
		Temp temp = new Temp();
		temp.setName("temp");
		ts.send(temp);
	}
	
	
	@GetMapping("queue")
	public void queue() {
		Temp temp = new Temp();
		temp.setName("temp");
		qs.send(temp);
	}
}
