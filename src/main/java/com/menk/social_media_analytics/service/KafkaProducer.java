package com.menk.social_media_analytics.service;

import com.menk.social_media_analytics.model.SocialMediaPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final String TOPIC = "social-media-data";

    @Autowired
    private KafkaTemplate<String, SocialMediaPost> kafkaTemplate;

    public void sendMessage(SocialMediaPost message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
