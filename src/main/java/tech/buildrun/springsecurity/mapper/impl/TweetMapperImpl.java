package tech.buildrun.springsecurity.mapper.impl;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import tech.buildrun.springsecurity.domain.dto.CreateTweetDto;
import tech.buildrun.springsecurity.domain.entity.TweetEntity;
import tech.buildrun.springsecurity.domain.entity.UserEntity;
import tech.buildrun.springsecurity.mapper.TweetMapper;

@Component
public class TweetMapperImpl implements TweetMapper {
    @Override
    public TweetEntity CreateUserDTOToUserEntity(CreateTweetDto dto, UserEntity userEntity) {
        TweetEntity tweetEntity = new TweetEntity();
        tweetEntity.setUser(userEntity);
        tweetEntity.setContent(dto.content());
        return tweetEntity;
    }
}
