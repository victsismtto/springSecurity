package tech.buildrun.springsecurity.service;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import tech.buildrun.springsecurity.domain.dto.CreateTweetDto;
import tech.buildrun.springsecurity.domain.dto.FeedDto;

public interface TweetService {
    FeedDto buildFeed(int page, int pageSize);
    void create(CreateTweetDto dto, JwtAuthenticationToken token);
    void delete(Long tweetId, JwtAuthenticationToken token);
}
