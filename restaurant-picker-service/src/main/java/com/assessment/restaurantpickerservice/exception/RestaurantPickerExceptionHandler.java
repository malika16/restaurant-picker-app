package com.assessment.restaurantpickerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestaurantPickerExceptionHandler {

    @ExceptionHandler(RestaurantPickerException.class)
    public ResponseEntity<RestauranError> handleCustomException(RestaurantPickerException ex, WebRequest request) {
        HttpStatus status = (null != ex.getHttpStatus()) ? ex.getHttpStatus() : HttpStatus.INTERNAL_SERVER_ERROR;
        RestauranError errorResponse = new RestauranError(status.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestauranError> handleGlobalException(Exception ex, WebRequest request) {
        RestauranError errorResponse = new RestauranError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred: " + ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

