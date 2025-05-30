package com.algaworks.algapost.text.processor.service.infrastructure.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_POST_PROCESSING = "text-processor-service.post-processing.v1.q";

    public static final String FANOUT_EXCHANGE_POST_PROCESSING_RESULT = "text-processor-service.post-processing-result.v1.e";


    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(ObjectMapper objectMapper){
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public Queue queue(){
        return QueueBuilder.durable(QUEUE_POST_PROCESSING).build();
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(exchangePostProcessing());
    }

    @Bean
    public FanoutExchange exchangePostProcessingResult(){
        return ExchangeBuilder.fanoutExchange(
                FANOUT_EXCHANGE_POST_PROCESSING_RESULT
        ).build();
    }

    public FanoutExchange exchangePostProcessing(){
        return ExchangeBuilder.fanoutExchange(
                "post-service.post-processing.v1.e"
        ).build();
    }

}
