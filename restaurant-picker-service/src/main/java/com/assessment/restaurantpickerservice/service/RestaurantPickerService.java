package com.assessment.restaurantpickerservice.service;

import com.assessment.model.SessionCreationResponse;
import com.assessment.model.SessionDetail;
import com.assessment.restaurantpickerservice.exception.RestaurantPickerException;
import com.assessment.restaurantpickerservice.repository.InvitationRepository;
import com.assessment.restaurantpickerservice.repository.RestaurantRepository;
import com.assessment.restaurantpickerservice.repository.SessionRepository;
import com.assessment.restaurantpickerservice.repository.model.Invitation;
import com.assessment.restaurantpickerservice.repository.model.Session;
import com.assessment.restaurantpickerservice.repository.model.SessionRestaurant;
import com.assessment.restaurantpickerservice.validate.RestaurantPickerValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.assessment.restaurantpickerservice.util.Constants.*;

@Service
@Slf4j
public class RestaurantPickerService {

    private final RestaurantRepository restaurantRepository;
    private final SessionRepository sessionRepository;
    private final InvitationRepository invitationRepository;
    private final RestaurantPickerValidator restaurantPickerValidator;

    @Autowired
    public RestaurantPickerService(RestaurantRepository restaurantRepository, SessionRepository sessionRepository,  InvitationRepository invitationRepository , RestaurantPickerValidator restaurantPickerValidator) {
        this.restaurantRepository = restaurantRepository;
        this.sessionRepository = sessionRepository;
        this.invitationRepository =  invitationRepository;
        this.restaurantPickerValidator = restaurantPickerValidator;
    }

    public SessionCreationResponse createSession(SessionDetail sessionCreationRequest) {
        Session session = new Session();
        session.setInitiator(sessionCreationRequest.getInitiator());
        session.setSessionId( SIMPLE_DATE_FORMAT.format(new Date()) + UNDER_SCORE + sessionCreationRequest.getInitiator());
        Session savedSession = sessionRepository.save(session);
        SessionCreationResponse response = new SessionCreationResponse();
        response.setSessionId(savedSession.getSessionId());
        log.info("Exit");
        return response;
    }

    public void sendInvitations(String sessionId, List<String> userIds) {
        //TODO: Validate the user IDs to check if they exist in the database, skipped the code as of now
        Session session = sessionRepository.findBySessionId(sessionId);

        restaurantPickerValidator.validateSession(session);
        userIds.stream()
                .map(user -> new Invitation(sessionId, user))
                .forEach(invitationRepository::save);
    }


    public void submitRestaurantChoice(String sessionId, String restaurantName) {
        Session session = sessionRepository.findBySessionId(sessionId);

        restaurantPickerValidator.validateSession(session);

        SessionRestaurant restaurant = new SessionRestaurant();
        restaurant.setRestaurantName(restaurantName);

        // Set the session for the restaurant choice
        restaurant.setSessionId(sessionId);

        // Save the restaurant choice to the database
        restaurantRepository.save(restaurant);
    }

    public List<String> getSubmittedRestaurants(String sessionId) {
        Session session = sessionRepository.findBySessionId(sessionId);
        restaurantPickerValidator.validateSession(session);
        List<SessionRestaurant> sessionRestaurants = restaurantRepository.findBySessionId(sessionId);
        if (sessionRestaurants.isEmpty()){
            throw new RestaurantPickerException("No restaurant choice submitted");
        }
        return sessionRestaurants.stream().map(r-> r.getRestaurantName()).collect(Collectors.toList());

    }

    public String pickRandomRestaurant(String sessionId, SessionDetail sessionDetail) {
        Session session = sessionRepository.findBySessionId(sessionId);

        restaurantPickerValidator.validateSession(session);
        restaurantPickerValidator.validateSessionInitiator(sessionDetail, session);

        List<SessionRestaurant> submittedRestaurants = restaurantRepository.findBySessionId(sessionId);
        if (submittedRestaurants.isEmpty()){
           throw new RestaurantPickerException("No restaurant choice submitted");
        }

        Random random = new Random();
        int randomIndex = random.nextInt(submittedRestaurants.size());

        session.setStatus(SESSION_CLOSED);
        sessionRepository.save(session);

        String randomRestaurant = submittedRestaurants.stream().map(r-> r.getRestaurantName()).collect(Collectors.toList()).get(randomIndex);
        return randomRestaurant;
    }


}

