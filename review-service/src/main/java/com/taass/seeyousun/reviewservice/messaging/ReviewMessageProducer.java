package com.taass.seeyousun.reviewservice.messaging;

import com.taass.seeyousun.reviewservice.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ReviewMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Long resortId, BigDecimal averageRating){
        ReviewMessageDTO reviewMessageDTO = ReviewMessageDTO.builder()
                .resortId(resortId)
                .averageRating(averageRating)
                .build();
        rabbitTemplate.convertAndSend(RabbitMQConfig.resortReviewQueue, reviewMessageDTO);
    }
}
