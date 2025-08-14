package com.supermarket.demo.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {
    private HttpStatus code;
    private String message;
    private LocalDateTime time;
}
