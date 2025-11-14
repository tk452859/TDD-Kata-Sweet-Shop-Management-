package org.example.sweet_shop.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class AuthRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
