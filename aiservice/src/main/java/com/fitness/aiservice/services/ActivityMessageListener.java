package com.fitness.aiservice.services;

import com.fitness.aiservice.models.Activity;
import com.fitness.aiservice.models.Recommendation;
import com.fitness.aiservice.repositories.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {

    private final ActivityAIService aiService;
    private final RecommendationRepository recommendationRepository;


    @KafkaListener(topics = "${kafka.topic.name}" , groupId = "activity-processor-group")
    public void processActivity(Activity activity) {
        log.info("Received activity for processing: {}", activity.getUserId());
        Recommendation recommendation=aiService.generateRecommendation(activity);
        recommendationRepository.save(recommendation);
    }
}