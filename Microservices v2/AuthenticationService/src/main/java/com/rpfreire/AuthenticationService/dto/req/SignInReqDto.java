package com.rpfreire.AuthenticationService.dto.req;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record SignInReqDto(@NotBlank String username, @NotBlank String password, @Valid AuthCreateRoleReqDto role) {
}