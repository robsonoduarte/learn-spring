package br.com.mystudies.springboot.jms.activemq.config;

import javax.jms.ConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JMSConfig {
	
    @Bean
    public JmsListenerContainerFactory<?> topicListenerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setMessageConverter(messageConverter());
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true); // enable topic to listener factory
        return factory;
    }
    
    // default listener factory is in queue
    @Bean
    public JmsListenerContainerFactory<?> queueListenerFactory(ConnectionFactory connectionFactory) {
    	DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    	factory.setMessageConverter(messageConverter());
    	factory.setConnectionFactory(connectionFactory);
    	return factory;
    }

    
	@Bean
	public JmsTemplate jmsTemplateTopic(ConnectionFactory connectionFactory){
	    JmsTemplate template = new JmsTemplate();
	    template.setConnectionFactory(connectionFactory);
	    template.setMessageConverter(messageConverter());
	    template.setPubSubDomain(true); // enable jsmTemplate to send in topic
	    return template;
	}
	

    // default jmsTemplate is in queue
	@Bean
	public JmsTemplate jmsTemplateQueue(ConnectionFactory connectionFactory){
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory);
		template.setMessageConverter(messageConverter());
		return template;
	}
	
	
	@Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

}
