package com.menk.social_media_analytics.service;



import com.menk.social_media_analytics.model.SocialMediaPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class LinkedInService {

    @Autowired
    private WebClient webClient;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;


    public void fetchAndProcessLinkedInData(Authentication authentication) {
        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(
                "linkedin", authentication.getName());

        if (authorizedClient != null) {
            Mono<SocialMediaPost> response = webClient
                    .get()
                    .uri("https://api.linkedin.com/v2/me")
                    .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(authorizedClient))
                    .retrieve()
                    .bodyToMono(SocialMediaPost.class);

            response.subscribe(post -> {
                // Customize this part to parse the LinkedIn response correctly
                post.setPlatform("LinkedIn");
                post.setTimestamp(System.currentTimeMillis());

                // Send message to Kafka
                kafkaProducer.sendMessage(post);
            });
        } else {
            throw new RuntimeException("No authorized LinkedIn client found for the user");
        }
    }



}
