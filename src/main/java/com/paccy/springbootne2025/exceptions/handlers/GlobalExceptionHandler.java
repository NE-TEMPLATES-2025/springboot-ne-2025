package com.paccy.springbootne2025.exceptions.handlers;


import com.paccy.springbootne2025.exceptions.BadRequestException;
import com.paccy.springbootne2025.exceptions.ResourceNotFoundException;
import com.paccy.springbootne2025.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@AllArgsConstructor
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public void handleResourceNotFoundException(Exception e){

    }

    @ExceptionHandler(UserNotFoundException.class)
    public void handleUserNotFoundException(Exception e){

    }

    @ExceptionHandler(BadRequestException.class)
    public void handleBadRequestException(Exception e){

    }
}
