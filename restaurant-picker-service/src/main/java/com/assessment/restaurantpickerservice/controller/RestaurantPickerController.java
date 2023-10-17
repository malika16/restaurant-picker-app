package com.assessment.restaurantpickerservice.controller;

import com.assessment.api.RestaurantPickerApi;
import com.assessment.model.*;
import com.assessment.restaurantpickerservice.service.RestaurantPickerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.assessment.restaurantpickerservice.util.Constants.COMMA;

@RestController
//@CrossOrigin(maxAge = 3600)
@Slf4j
public class RestaurantPickerController implements RestaurantPickerApi {
    
    private RestaurantPickerService restaurantPickerService;

    @Autowired
    public RestaurantPickerController(RestaurantPickerService restaurantPickerService) {
        this.restaurantPickerService = restaurantPickerService;
    }

    @Override
    public CompletableFuture<ResponseEntity<SessionCreationResponse>> sessions(SessionDetail sessionCreationRequest) {
        ResponseEntity<SessionCreationResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(restaurantPickerService.createSession(sessionCreationRequest));
        return CompletableFuture.completedFuture(body);
    }

    @Override
    public CompletableFuture<ResponseEntity<Void>> inviteUsers(String sessionId, String userIds) {
        try {
            List<String> userList = Arrays.asList(userIds.split(COMMA));
            if (CollectionUtils.isEmpty(userList)) {
                return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
            }
            restaurantPickerService.sendInvitations(sessionId, userList);
            return CompletableFuture.completedFuture(ResponseEntity.ok().build());
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
        }
    }

    @Override
    public CompletableFuture<ResponseEntity<Void>> restaurants(String sessionId, RestaurantSubmissionRequest restaurantSubmissionRequest) {
        restaurantPickerService.submitRestaurantChoice(sessionId, restaurantSubmissionRequest.getRestaurantName());
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Override
    public CompletableFuture<ResponseEntity<RestaurantListResponse>> submittedRestaurants(String sessionId) {
        List<String> submittedRestaurants = restaurantPickerService.getSubmittedRestaurants(sessionId);
        RestaurantListResponse restaurantListResponse = new RestaurantListResponse();
        restaurantListResponse.setRestaurant(submittedRestaurants);
        return CompletableFuture.completedFuture(ResponseEntity.ok().body(restaurantListResponse));
    }

    @Override
    public CompletableFuture<ResponseEntity<SessionEndResponse>> pickRestaurant(String sessionId, SessionDetail sessionDetail) {
        String randomRestaurant = restaurantPickerService.pickRandomRestaurant(sessionId, sessionDetail);
        SessionEndResponse response = new SessionEndResponse();
        response.setPickedRestaurant(randomRestaurant);
        return CompletableFuture.completedFuture(ResponseEntity.ok().body(response));
    }
}
