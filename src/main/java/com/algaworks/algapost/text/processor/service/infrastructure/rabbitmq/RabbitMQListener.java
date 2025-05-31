package com.algaworks.algapost.text.processor.service.infrastructure.rabbitmq;

import com.algaworks.algapost.text.processor.service.api.model.PostProcessingRequestMessage;
import com.algaworks.algapost.text.processor.service.model.service.PostProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQListener {

    private final PostProcessingService service;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_POST_PROCESSING)
    public void handle(@Payload PostProcessingRequestMessage postProcessingRequestMessage) {
        service.processAndSendResult(postProcessingRequestMessage);
    }
}
