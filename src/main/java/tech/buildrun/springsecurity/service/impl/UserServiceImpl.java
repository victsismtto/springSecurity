package tech.buildrun.springsecurity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tech.buildrun.springsecurity.domain.dto.CreateUserDto;
import tech.buildrun.springsecurity.domain.entity.RoleEntity;
import tech.buildrun.springsecurity.domain.entity.UserEntity;
import tech.buildrun.springsecurity.mapper.UserMapper;
import tech.buildrun.springsecurity.repository.RoleRepository;
import tech.buildrun.springsecurity.repository.UserRepository;
import tech.buildrun.springsecurity.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired private BCryptPasswordEncoder passwordEncoder;
    @Autowired private UserMapper mapper;
    @Autowired private UserRepository repository;
    @Autowired private RoleRepository roleRepository;

    @Override
    public void create(CreateUserDto dto) {

        RoleEntity basicRoleEntity = roleRepository.findByName(RoleEntity.Values.BASIC.name());

        Optional<UserEntity> userFromDb = repository.findByUsername(dto.username());
        if (userFromDb.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        repository.save(mapper.CreateUserDTOToUserEntity(dto, basicRoleEntity));
    }

    @Override
    public List<UserEntity> getList() {
        return repository.findAll();
    }
}
