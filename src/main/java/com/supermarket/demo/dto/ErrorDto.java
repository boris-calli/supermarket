package com.supermarket.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {
    @NotNull
    @NotBlank
    private String code;

    @NotNull
    @NotBlank
    private String message;
}
