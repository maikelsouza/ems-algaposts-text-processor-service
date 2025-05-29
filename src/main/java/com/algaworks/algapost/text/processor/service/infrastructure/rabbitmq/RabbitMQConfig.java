package com.algaworks.algapost.text.processor.service.infrastructure.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_POST_PROCESSING = "text-processor-service.post-processing.v1.q";


    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }


    @Bean
    public Queue queue(){
        return QueueBuilder.durable(QUEUE_POST_PROCESSING).build();
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(exchange());
    }


    public FanoutExchange exchange(){
        return ExchangeBuilder.fanoutExchange(
                "post-service.post-processing.v1.e"
        ).build();
    }
}
