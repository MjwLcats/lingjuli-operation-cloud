package com.lingjuli.identity.interfaces;

import com.lingjuli.foundation.webmvc.ApiResponse;
import com.lingjuli.identity.application.LoginCommand;
import com.lingjuli.identity.application.LoginService;
import com.lingjuli.identity.domain.AccountType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
    private final LoginService loginService;

    public AuthController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/platform/auth/login")
    public ApiResponse<LoginResponse> platformLogin(@Valid @RequestBody LoginRequest request, HttpServletRequest servletRequest) {
        return login(request, AccountType.PLATFORM, servletRequest);
    }

    @PostMapping("/merchant/auth/login")
    public ApiResponse<LoginResponse> merchantLogin(@Valid @RequestBody LoginRequest request, HttpServletRequest servletRequest) {
        return login(request, AccountType.MERCHANT, servletRequest);
    }

    @GetMapping("/auth/session")
    public ApiResponse<CurrentSessionResponse> session(@RequestHeader("Authorization") String authorization, HttpServletRequest request) {
        return ApiResponse.success(CurrentSessionResponse.from(loginService.requireSession(bearerToken(authorization))), requestId(request));
    }

    @PostMapping("/auth/logout")
    public ApiResponse<Void> logout(@RequestHeader("Authorization") String authorization, HttpServletRequest request) {
        loginService.logout(bearerToken(authorization));
        return ApiResponse.success(null, requestId(request));
    }

    private ApiResponse<LoginResponse> login(LoginRequest request, AccountType accountType, HttpServletRequest servletRequest) {
        LoginCommand command = new LoginCommand(accountType, request.merchantNo(), request.loginName().trim(), request.password(),
            servletRequest.getRemoteAddr(), servletRequest.getHeader("User-Agent"));
        return ApiResponse.success(LoginResponse.from(loginService.login(command)), requestId(servletRequest));
    }

    private String bearerToken(String authorization) {
        if (authorization == null || !authorization.startsWith("Bearer ") || authorization.length() <= 7) {
            return "";
        }
        return authorization.substring(7);
    }

    private String requestId(HttpServletRequest request) {
        return request.getHeader("X-Request-Id");
    }
}
