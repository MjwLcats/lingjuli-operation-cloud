package com.lingjuli.identity.interfaces;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record LoginRequest(
    @Positive Long merchantNo,
    @NotBlank @Size(max = 64) String loginName,
    @NotBlank @Size(max = 128) String password
) {}
