package com.assessment.restaurantpickerservice.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class RestaurantPickerException extends RuntimeException {
    private HttpStatus httpStatus;

    public RestaurantPickerException(String message) {
        super(message);
    }

    public RestaurantPickerException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
