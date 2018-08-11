package ofs.config;

import java.util.concurrent.Executors;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

	@Value("${queue.reply}")
	private String replyQueue;
	@Value("${exchange.direct}")
	private String directExchange;
	@Value("${routingKey.reply}")
	private String replyRoutingKey;

	@Bean
	public SimpleRabbitListenerContainerFactory simpleMessageListenerContainerFactory(
			ConnectionFactory connectionFactory, SimpleRabbitListenerContainerFactoryConfigurer configurer) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();

		configurer.configure(factory, connectionFactory);
		return factory;
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(jsonMessageConverter());
		return template;
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public Queue replyQueueRPC() {
		return new Queue(replyQueue);
	}

	@Bean
	public SimpleMessageListenerContainer rpcReplyMessageListenerContainer(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(
				connectionFactory);
		simpleMessageListenerContainer.setQueues(replyQueueRPC());
		simpleMessageListenerContainer.setReceiveTimeout(2000);
		simpleMessageListenerContainer.setTaskExecutor(Executors.newCachedThreadPool());
		return simpleMessageListenerContainer;
	}

	@Bean
	public AsyncRabbitTemplate asyncRabbitTemplate(ConnectionFactory connectionFactory) {

		return new AsyncRabbitTemplate(rabbitTemplate(connectionFactory),
				rpcReplyMessageListenerContainer(connectionFactory), directExchange + "/" + replyRoutingKey);
	}

	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange(directExchange);
	}

	@Bean
	public Binding binding() {
		return BindingBuilder.bind(replyQueueRPC()).to(directExchange()).with(replyRoutingKey);
	}

}
