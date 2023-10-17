package com.assessment.restaurantpickerservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestauranError {
    private int code;
    private String message;
}

