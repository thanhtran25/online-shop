package com.onlineshop.controller;

import com.onlineshop.dto.request.*;
import com.onlineshop.dto.response.ApiResponse;
import com.onlineshop.dto.response.UserResponse;
import com.onlineshop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ApiResponse<List<UserResponse>> getUsers() {
        log.info("getUsers");
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("authentication: {}", authentication.getName());
        authentication.getAuthorities()
                .forEach(grantedAuthority -> log.info("grantedAuthority: {}", grantedAuthority));


        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> getUser(@PathVariable String userId) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(userId))
                .build();
    }

    @PutMapping("/{userId}")
    public ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody @Valid UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(userId, request))
                .build();
    }

    @DeleteMapping("{userId}")
    public ApiResponse deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ApiResponse.builder()
                .build();
    }
}
