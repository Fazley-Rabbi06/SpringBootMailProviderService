package com.mailservice.mailservice.controller;

import com.mailservice.mailservice.domain.User;
import com.mailservice.mailservice.response.HttpResponse;
import com.mailservice.mailservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<HttpResponse> createUser(@RequestBody User user) {
        User savedUser;
        try {
            savedUser = userService.saveUser(user);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(HttpResponse
                            .builder()
                            .timestamp(LocalDateTime.now().toString())
                            .data(Map.of("user", savedUser))
                            .message("User Created Successfully.")
                            .statusCode(HttpStatus.CREATED.value())
                            .build());
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(HttpResponse
                            .builder()
                            .message("Failed to create user.")
                            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .data(Map.of("Error", ex.getMessage()))
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .build());
        }
    }

    @GetMapping
    public ResponseEntity<HttpResponse> verifyUser(@RequestParam String token) {
        Boolean isSuccessful;
        try {
            isSuccessful = userService.verifyToken(token);
            return ResponseEntity
                    .ok()
                    .body(HttpResponse
                            .builder()
                            .timestamp(LocalDateTime.now().toString())
                            .data(Map.of("Success", isSuccessful))
                            .message("Account verified.")
                            .statusCode(HttpStatus.OK.value())
                            .build());
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(HttpResponse
                            .builder()
                            .message("Verification failed.")
                            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .data(Map.of("Error", ex.getMessage()))
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .build());
        }
    }

}
