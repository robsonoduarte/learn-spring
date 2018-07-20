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
        factory.setPubSubDomain(true);
        return factory;
    }
    
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
	    template.setPubSubDomain(true);
	    return template;
	}
	
	
	@Bean
	public JmsTemplate jmsTemplateQueue(ConnectionFactory connectionFactory){
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory);
		template.setMessageConverter(messageConverter());
		//template.setPubSubDomain(true);
		return template;
	}
	
	
	
	@Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        //converter.setObjectMapper(objectMapper());
        return converter;
    }

/*    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }*/
	
}
