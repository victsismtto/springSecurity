package tech.buildrun.springsecurity.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.buildrun.springsecurity.domain.dto.CreateUserDto;
import tech.buildrun.springsecurity.domain.entity.UserEntity;
import tech.buildrun.springsecurity.service.UserService;

import java.util.List;

@RestController
public class UserController {

    @Autowired private UserService service;

    @Transactional
    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto dto) {
        service.create(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<UserEntity>> listUsers() {
        return ResponseEntity.ok(service.getList());
    }
}
