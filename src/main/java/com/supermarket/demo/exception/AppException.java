package com.supermarket.demo.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.supermarket.demo.dto.ErrorDto;

@ControllerAdvice
public class AppException {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> handleResourceNotFoundException(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        ErrorDto error = ErrorDto.builder()
        .code(ex.getStatus())
        .message(message)
        .build();
        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(ResourceExistsException.class)
    public ResponseEntity<ErrorDto> handleResourceExistsException(ResourceExistsException ex) {
        String message = ex.getMessage();
        ErrorDto error = ErrorDto.builder()
        .code(ex.getStatus())
        .message(message)
        .time(LocalDateTime.now())
        .build();
        return new ResponseEntity<>(error, ex.getStatus());
    }

    // MethodArgumentNotValidException
    // {
    //     "username": "",
    //     "password": "123",
    //     "name": "Alejandro",
    //     "lastName": "Pereira"
    // }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String,Object> error = new HashMap<>();        
        HttpStatus status = HttpStatus.resolve(ex.getStatusCode().value());

        Map<String,String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            String field = fieldError.getField();
            String fieldMessage = fieldError.getDefaultMessage();
            fieldErrors.put(field, fieldMessage);
        });
        error.put("error", status.toString());
        error.put("fields", fieldErrors);
        error.put("time", LocalDateTime.now().toString());

        return ResponseEntity.status(status).body(error);
    }

    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // public ResponseEntity<ErrorDto> handleValidException(MethodArgumentNotValidException ex) {
    //     ErrorDto error = ErrorDto.builder()
    //     .code(HttpStatus.BAD_REQUEST)
    //     .message("Fallido")
    //     .time(LocalDateTime.now())
    //     .build();
    //     return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    // }
    
    
    // MethodArgumentTypeMismatchException
    // http://localhost:8080/api/users/abc
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        Map<String,String> error = new HashMap<>();

        String dataTypeExpectedName= ex.getRequiredType().getSimpleName();
        String parameterName = ex.getName();
        String parameterValueReceived = ex.getValue().toString();
        String dataTypeReceivedName = ex.getValue().getClass().getSimpleName();
        String errorCode = ex.getErrorCode();
        String message = String.format("Paramether for \"%s\" expected a \"%s\" but received a \"%s\" with value: %s" , parameterName, dataTypeExpectedName, dataTypeReceivedName, parameterValueReceived);
        
        error.put("error", errorCode);
        error.put("data type expected", dataTypeExpectedName);
        error.put("parameter", parameterName);
        error.put("message", message);
        error.put("time", LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // PropertyReferenceException
    // query sort: abc
    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<Map<String,String>> handlePropertyReferenceException(PropertyReferenceException ex) {
        Map<String,String> error = new HashMap<>();

        HttpStatus errorCode = HttpStatus.BAD_REQUEST;
        String fieldOfEntity = ex.getPropertyName();
        String entityName = ex.getType().getType().getSimpleName();
        String message = String.format("No property or field \"%s\" found for type \"%s\"", fieldOfEntity, entityName);
        
        error.put("error", errorCode.toString());
        error.put("property", fieldOfEntity);
        error.put("entity", entityName);
        error.put("message", message);
        error.put("time", LocalDateTime.now().toString());

        return ResponseEntity.status(errorCode).body(error);
    }
    // IllegalArgumentException
    // query direction: abec
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String,String> error = new HashMap<>();

        HttpStatus errorCode = HttpStatus.BAD_REQUEST;
        error.put("error", errorCode.toString());
        error.put("message", ex.getMessage());

        return ResponseEntity.status(errorCode).body(error);
    }

    // HttpMessageNotReadableException
    //     {
    //   "username": "Alejandrolopez123",
    //   "password": "password": 
    //   "name": "Miguel",
    //   "lastName": "Calli Rodriguez"
    // }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String,String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Map<String, String> error = new HashMap<>();

        HttpStatus errorCode = HttpStatus.BAD_REQUEST;
        error.put("error", String.format("%s Unreadable request content", errorCode.toString()));
        error.put("message", ex.getMessage());
        error.put("time", LocalDateTime.now().toString());

        return ResponseEntity.status(errorCode).body(error);
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<ErrorDto> handleMissingPathVariable(MissingPathVariableException ex) {
        String message = String.format("Path variable required is missing: %s", ex.getVariableName());
        HttpStatus status = HttpStatus.resolve(ex.getStatusCode().value());
        ErrorDto error = ErrorDto.builder()
            .code(status)
            .message(message)
            .time(LocalDateTime.now())
            .build();
        return new ResponseEntity<>(error, status);
    }
}
