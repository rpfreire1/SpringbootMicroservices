package com.rpfreire.AuthenticationService.dto.req;

import jakarta.validation.constraints.NotBlank;

public record LogInReqDto(@NotBlank String username, @NotBlank String password) {
}
