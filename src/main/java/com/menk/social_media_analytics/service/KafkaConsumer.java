package com.menk.social_media_analytics.service;

import com.menk.social_media_analytics.model.SocialMediaPost;
import com.menk.social_media_analytics.repository.SocialMediaPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    private SocialMediaPostRepository socialMediaPostRepository;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @KafkaListener(topics = "social-media-data", groupId = "group_id", containerFactory = "kafkaListenerContainerFactory")
    public void consume(SocialMediaPost message) {

        String sentiment = sentimentAnalysisService.analyzeSertiment(message.getText());
        message.setSentiment(sentiment);
        socialMediaPostRepository.save(message);
        System.out.println("Consumed message :" + message);
    }
}
