package tech.buildrun.springsecurity.mapper;

import tech.buildrun.springsecurity.domain.dto.CreateUserDto;
import tech.buildrun.springsecurity.domain.entity.RoleEntity;
import tech.buildrun.springsecurity.domain.entity.UserEntity;

public interface UserMapper {

    UserEntity CreateUserDTOToUserEntity(CreateUserDto dto, RoleEntity basicRoleEntity);
}
