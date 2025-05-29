package com.paccy.springbootne2025.exceptions.handlers;

import com.paccy.springbootne2025.exceptions.BadRequestException;
import com.paccy.springbootne2025.exceptions.ResourceNotFoundException;
import com.paccy.springbootne2025.exceptions.UnauthorizedException;
import com.paccy.springbootne2025.exceptions.UserNotFoundException;
import com.paccy.springbootne2025.response.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ControllerAdvice
@ResponseBody
@AllArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException e) {
        return buildResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFoundException(UserNotFoundException e) {
        return buildResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequestException(BadRequestException e) {
        return buildResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("status", status.value());
        errorDetails.put("error", status.getReasonPhrase());
        errorDetails.put("message", message);
        return new ResponseEntity<>(errorDetails, status);
    }
}

//@ControllerAdvice
//@ResponseBody
//@AllArgsConstructor
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class GlobalExceptionHandler {
//    private final MessageSource messageSource;
//
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e, Locale locale) {
//        String errorMessage= messageSource.getMessage(e.getMessage(),null,locale);
//        return  new ApiResponse<>(null,HttpStatus.NOT_FOUND,errorMessage).toResponseEntity();
//    }
//
//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException e,Locale locale) {
//        String errorMessage= messageSource.getMessage(e.getMessage(),null,locale);
//        return  new ApiResponse<>(null,HttpStatus.NOT_FOUND,errorMessage).toResponseEntity();
//    }
//
//    @ExceptionHandler(BadRequestException.class)
//    public ResponseEntity<?> handleBadRequestException(BadRequestException e,Locale locale) {
//        String errorMessage= messageSource.getMessage(e.getMessage(),null,locale);
//        return  new ApiResponse<>(null,HttpStatus.BAD_REQUEST,errorMessage).toResponseEntity();
//    }
//
//    @ExceptionHandler(UnauthorizedException.class)
//    public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException e, Locale locale) {
//        String errorMessage= messageSource.getMessage(e.getMessage(),null,locale);
//        return  new ApiResponse<>(null,HttpStatus.UNAUTHORIZED,errorMessage).toResponseEntity();
//    }
//
//
//    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message) {
//        Map<String, Object> errorDetails = new HashMap<>();
//        errorDetails.put("timestamp", LocalDateTime.now());
//        errorDetails.put("status", status.value());
//        errorDetails.put("error", status.getReasonPhrase());
//        errorDetails.put("message", message);
//        return new ResponseEntity<>(errorDetails, status);
//    }
//}
