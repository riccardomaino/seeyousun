package com.taass.seeyousun.reviewservice.messaging;

import com.taass.seeyousun.reviewservice.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Long resortId, Double averageRating){
        ReviewMessageDTO reviewMessageDTO = ReviewMessageDTO.builder()
                .resortId(resortId)
                .averageRating(averageRating)
                .build();
        rabbitTemplate.convertAndSend(RabbitMQConfig.resortReviewQueue, reviewMessageDTO);
    }
}
