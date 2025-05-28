package com.paccy.springbootne2025.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse <T>{
     final LocalDateTime timeStamp= LocalDateTime.now();
     T data;
     String message;
     HttpStatus status;

    public ApiResponse(String  message, HttpStatus status, T data) {
        this.message=message;
        this.status=status;
        this.data=data;
    }

    public ResponseEntity<ApiResponse<T>> toResponseEntity(){
        assert status !=null;
        return  ResponseEntity.status(status).body(this);
    }
}
