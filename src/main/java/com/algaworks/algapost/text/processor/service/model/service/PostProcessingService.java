package com.algaworks.algapost.text.processor.service.model.service;

import com.algaworks.algapost.text.processor.service.api.model.PostProcessingRequestMessage;
import com.algaworks.algapost.text.processor.service.api.model.PostProcessingResultMessage;
import com.algaworks.algapost.text.processor.service.infrastructure.rabbitmq.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostProcessingService {

    private final PostProcessingCalculatorService calculatorService;

    private final RabbitTemplate rabbitTemplate;


    public void processAndSendResult(PostProcessingRequestMessage message) {

        PostProcessingResultMessage result = calculatorService.calculate(message);
        rabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE_POST_PROCESSING_RESULT, "", result);
        log.info("Published processing result: {}", result);
    }
}
