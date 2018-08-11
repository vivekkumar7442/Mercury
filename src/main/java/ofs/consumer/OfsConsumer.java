package ofs.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OfsConsumer {
	

	@RabbitListener(queues = "vivek Test")
	public void createOptyResponse(String responseBody) {
		try {
			
			System.out.println("----------------------------------------"+responseBody);
			
		} catch (Exception e) {
		}
	}


}
