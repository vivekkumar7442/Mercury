package ofs.utils;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public  class MqUtils {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	/**
	 * This method is used for synchronous publish
	 * 
	 * @param queue
	 * @param request
	 * @return
	 */
	public Object sendAndReceive(String queue, String request) {
		return rabbitTemplate.convertSendAndReceive(queue, request);
	}

	/**
	 * This is used for asynchronous publish
	 * 
	 * @param queue
	 * @param request
	 */
	public void send(String queue, String request) {
		rabbitTemplate.convertAndSend(queue, request);
	}

}
