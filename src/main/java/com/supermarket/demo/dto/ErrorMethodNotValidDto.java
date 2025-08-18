package com.supermarket.demo.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorMethodNotValidDto {
    private String fields;
    private String message;
    private String code;
}
