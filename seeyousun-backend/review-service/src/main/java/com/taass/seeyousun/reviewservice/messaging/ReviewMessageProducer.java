package com.taass.seeyousun.reviewservice.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class ReviewMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

//    public void sendMessage(Review review){
//        ReviewMessageDTO reviewMessageDTO = ReviewMessageDTO.builder()
//                .id(review.getId())
//                .title(review.getTitle())
//                .description(review.getDescription())
//                .rating(review.getRating())
//                .resortId(review.getResortId())
//                .build();
//        rabbitTemplate.convertAndSend(RabbitMQConfiguration.resortReviewQueue, reviewMessageDTO);
//    }
}
