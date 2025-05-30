package com.algaworks.algapost.text.processor.service.api.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PostProcessingResultMessage {

    private UUID postId;

    private int wordCount;

    private double calculatedValue;
}
