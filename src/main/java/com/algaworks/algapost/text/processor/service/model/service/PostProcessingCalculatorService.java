package com.algaworks.algapost.text.processor.service.model.service;

import com.algaworks.algapost.text.processor.service.api.model.PostProcessingRequestMessage;
import com.algaworks.algapost.text.processor.service.api.model.PostProcessingResultMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostProcessingCalculatorService {

        public PostProcessingResultMessage calculate(PostProcessingRequestMessage postProcessingRequestMessage) {
            int wordCount = countWords(postProcessingRequestMessage.getPostBody());
            double calculatedValue = wordCount * 0.10;

            return  PostProcessingResultMessage.builder()
                    .postId(postProcessingRequestMessage.getPostId())
                    .wordCount(wordCount)
                    .calculatedValue(calculatedValue)
                    .build();
        }

        private int countWords(String text) {
            return text.trim().isEmpty() ? 0 : text.trim().split("\\s+").length;
        }
}
