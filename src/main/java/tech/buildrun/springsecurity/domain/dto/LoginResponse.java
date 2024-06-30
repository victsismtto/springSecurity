package tech.buildrun.springsecurity.domain.dto;

public record LoginResponse(String accessToken, Long expiresIn) {
}
