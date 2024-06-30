package tech.buildrun.springsecurity.service;

import tech.buildrun.springsecurity.domain.dto.CreateUserDto;
import tech.buildrun.springsecurity.domain.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> getList();
    void create(CreateUserDto dto);

}
