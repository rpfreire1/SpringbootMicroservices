package com.rpfreire.AuthenticationService.dto.req;

import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public record AuthCreateRoleReqDto(
        @Size(max = 3, message = "The user cannot have more than 3 roles") List<String> roles
) {
}
