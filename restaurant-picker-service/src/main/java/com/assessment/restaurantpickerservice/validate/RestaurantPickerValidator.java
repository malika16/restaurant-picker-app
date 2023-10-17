package com.assessment.restaurantpickerservice.validate;

import com.assessment.model.SessionDetail;
import com.assessment.restaurantpickerservice.exception.RestaurantPickerException;
import com.assessment.restaurantpickerservice.repository.model.Session;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.assessment.restaurantpickerservice.util.Constants.SESSION_CLOSED;

@Service
public class RestaurantPickerValidator {

    public void validateSessionInitiator(SessionDetail sessionDetail, Session session) {
        if (!sessionDetail.getInitiator().equalsIgnoreCase(session.getInitiator())) {
            throw new RestaurantPickerException(HttpStatus.BAD_REQUEST, "Session can be ended by initiator only");
        }
    }

    public void validateSession(Session session) {
        if (session == null || SESSION_CLOSED.equalsIgnoreCase(session.getStatus())) {
            throw new RestaurantPickerException(HttpStatus.BAD_REQUEST, "Invalid Session Id");
        }
    }
}
