package com.menk.social_media_analytics.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SocialMediaPost {

    @Id
    private String id;
    private String text;
    private String username;
    private String platform;
    private String sentiment;
    private String profileUrl;
    private int likes;
    private int comments;
    private long timestamp;
}
