package com.taass.seeyousun.resortservice.messaging;

import com.taass.seeyousun.resortservice.services.ResortService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageConsumer {
    private final ResortService resortService;

    public ReviewMessageConsumer(ResortService resortService) {
        this.resortService = resortService;
    }

    @RabbitListener(queues = "resort-review-queue")
    public void consumeMessage(ReviewMessageDTO reviewMessageDTO){
        resortService.updateResortRating(reviewMessageDTO);
    }
}
