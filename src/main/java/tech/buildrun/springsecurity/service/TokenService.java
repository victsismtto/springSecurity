package tech.buildrun.springsecurity.service;

import tech.buildrun.springsecurity.domain.dto.LoginRequest;
import tech.buildrun.springsecurity.domain.dto.LoginResponse;

public interface TokenService {

    LoginResponse create(LoginRequest loginRequest);
}
