package tech.buildrun.springsecurity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tech.buildrun.springsecurity.domain.dto.CreateTweetDto;
import tech.buildrun.springsecurity.domain.dto.FeedDto;
import tech.buildrun.springsecurity.domain.dto.FeedItemDto;
import tech.buildrun.springsecurity.domain.entity.RoleEntity;
import tech.buildrun.springsecurity.domain.entity.TweetEntity;
import tech.buildrun.springsecurity.domain.entity.UserEntity;
import tech.buildrun.springsecurity.mapper.TweetMapper;
import tech.buildrun.springsecurity.repository.TweetRepository;
import tech.buildrun.springsecurity.repository.UserRepository;
import tech.buildrun.springsecurity.service.TweetService;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired private TweetMapper mapper;
    @Autowired private TweetRepository tweetRepository;
    @Autowired private UserRepository userRepository;

    @Override
    public FeedDto buildFeed(int page, int pageSize) {
        Page<FeedItemDto> tweets = tweetRepository.findAll(PageRequest.of(page, pageSize, Sort.Direction.DESC, "creationTimestamp"))
            .map(tweet -> new FeedItemDto(tweet.getTweetId(), tweet.getContent(), tweet.getUser().getUsername()));
        return new FeedDto(tweets.getContent(), page, pageSize, tweets.getTotalPages(), tweets.getTotalElements());
    }

    @Override
    public void create(CreateTweetDto dto, JwtAuthenticationToken token) {
        Optional<UserEntity> user = userRepository.findById(UUID.fromString(token.getName()));
        user.ifPresentOrElse(
            entity -> tweetRepository.save(mapper.CreateUserDTOToUserEntity(dto, entity)),
            () -> { throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY); }
        );
    }

    @Override
    public void delete(Long tweetId, JwtAuthenticationToken token) {
        Optional<UserEntity> user = userRepository.findById(UUID.fromString(token.getName()));
        TweetEntity tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        AtomicBoolean isAdmin = new AtomicBoolean(false);

        user.ifPresent(entity ->
                isAdmin.set(entity.getRoles().stream().anyMatch(role -> role.getName().equalsIgnoreCase(RoleEntity.Values.ADMIN.name()))));

        if (isAdmin.get() || tweet.getUser().getUserId().equals(UUID.fromString(token.getName()))) {
            tweetRepository.deleteById(tweetId);
        }
    }
}
