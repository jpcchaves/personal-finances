package com.jpcchaves.finances.controller;

import com.jpcchaves.finances.dto.user.LoginRequestDto;
import com.jpcchaves.finances.dto.user.LoginResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @PostMapping
    @Operation(summary = "Authenticate an user",
            description = "Authenticates a user by passing in a JSON representation of the user's email and password",
            tags = {"Authentication"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = LoginResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto user) {
        LoginResponseDto LoginResponseDto = new LoginResponseDto();
        return ResponseEntity.ok(LoginResponseDto);
    }

}
