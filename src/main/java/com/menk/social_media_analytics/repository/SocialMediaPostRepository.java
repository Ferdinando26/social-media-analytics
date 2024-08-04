package com.menk.social_media_analytics.repository;

import com.menk.social_media_analytics.model.SocialMediaPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialMediaPostRepository extends JpaRepository<SocialMediaPost, String> {

}
