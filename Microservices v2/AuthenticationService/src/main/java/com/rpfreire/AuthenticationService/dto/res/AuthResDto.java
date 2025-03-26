package com.rpfreire.AuthenticationService.dto.res;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username","message", "status","jwt"})
public record AuthResDto(
        String username,
        String message,
        Boolean status,
        String jwt
) {
}
