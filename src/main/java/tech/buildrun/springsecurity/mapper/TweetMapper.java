package tech.buildrun.springsecurity.mapper;

import tech.buildrun.springsecurity.domain.dto.CreateTweetDto;
import tech.buildrun.springsecurity.domain.entity.TweetEntity;
import tech.buildrun.springsecurity.domain.entity.UserEntity;

public interface TweetMapper {
    TweetEntity CreateUserDTOToUserEntity(CreateTweetDto dto, UserEntity userEntity);
}
