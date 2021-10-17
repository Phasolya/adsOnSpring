package com.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This is {@link ExceptionsHandler} class which contains
 * some exception handler for {@link com.controller.AdvertisementController}
 * {@link com.controller.CategoryController}
 * {@link com.controller.MatchingAdController}
 * {@link com.controller.UserController} classes etc...
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */
@ControllerAdvice
public class ExceptionsHandler {

    // TODO: 19.09.2021 CATCH ONLY VALID EXEPTIONS!!!
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handlerException(MethodArgumentNotValidException ex) {

        return new ResponseEntity<>(ex.getMessage() + "FROM HANDLER", HttpStatus.BAD_REQUEST);
    }
}
