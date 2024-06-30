package tech.buildrun.springsecurity.mapper.impl;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import tech.buildrun.springsecurity.domain.dto.CreateUserDto;
import tech.buildrun.springsecurity.domain.entity.RoleEntity;
import tech.buildrun.springsecurity.domain.entity.UserEntity;
import tech.buildrun.springsecurity.mapper.UserMapper;

import java.util.Set;

@Component
public class UserMapperImpl implements UserMapper {
    @Autowired private BCryptPasswordEncoder passwordEncoder;
    @Override
    public UserEntity CreateUserDTOToUserEntity(CreateUserDto dto, RoleEntity basicRoleEntity) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(dto.username());
        userEntity.setPassword(passwordEncoder.encode(dto.password()));
        userEntity.setRoles(Set.of(basicRoleEntity));
        return userEntity;
    }
}
