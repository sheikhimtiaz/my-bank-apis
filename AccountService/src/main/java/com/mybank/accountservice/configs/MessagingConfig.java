package com.mybank.accountservice.configs;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;

import static com.mybank.accountservice.constants.AppConstants.*;

public class MessagingConfig {

    @Bean
    public Queue queue(){
        return new Queue(TOPIC_EXCHANGE_ACCOUNT);
    }
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }
    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY_ACCOUNT);
    }

    @Bean
    private MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter messageListenerAdapter){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(TOPIC_EXCHANGE_ACCOUNT);
        container.setMessageListener(messageListenerAdapter);
        return container;
    }



    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
