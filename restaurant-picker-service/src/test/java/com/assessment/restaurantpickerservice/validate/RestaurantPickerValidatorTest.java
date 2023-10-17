package com.assessment.restaurantpickerservice.validate;

import com.assessment.restaurantpickerservice.exception.RestaurantPickerException;
import com.assessment.restaurantpickerservice.repository.model.Session;
import com.assessment.model.SessionDetail;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RestaurantPickerValidatorTest {

    @Test
    public void testValidateSessionInitiatorWithMatchingInitiator() {
        RestaurantPickerValidator validator = new RestaurantPickerValidator();
        SessionDetail sessionDetail = new SessionDetail();
        sessionDetail.setInitiator("malika");
        Session session = new Session();
        session.setInitiator("malika");

        validator.validateSessionInitiator(sessionDetail, session);
    }

    @Test
    public void testValidateSessionInitiatorWithDifferentInitiator() {
        RestaurantPickerValidator validator = new RestaurantPickerValidator();
        SessionDetail sessionDetail = new SessionDetail();
        sessionDetail.setInitiator("malika");
        Session session = new Session();
        session.setInitiator("test");
        assertThatThrownBy(() -> validator.validateSessionInitiator(sessionDetail, session))
                .isInstanceOf(RestaurantPickerException.class)
                .hasFieldOrPropertyWithValue("httpStatus", HttpStatus.BAD_REQUEST);
    }


    @Test
    public void testValidateSessionWithValidSession() {
        RestaurantPickerValidator validator = new RestaurantPickerValidator();
        Session session = new Session();
        session.setInitiator("malika");

        validator.validateSession(session);
    }

    @Test
    public void testValidateSessionWithClosedSession() {
        RestaurantPickerValidator validator = new RestaurantPickerValidator();
        Session session = new Session();
        session.setInitiator("malika");
        session.setStatus("closed");

        assertThatThrownBy(() -> validator.validateSession(session))
                .isInstanceOf(RestaurantPickerException.class)
                .hasFieldOrPropertyWithValue("httpStatus", HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testValidateSessionWithNullSession() {
        RestaurantPickerValidator validator = new RestaurantPickerValidator();
        Session session = null;

        assertThatThrownBy(() -> validator.validateSession(session))
                .isInstanceOf(RestaurantPickerException.class)
                .hasFieldOrPropertyWithValue("httpStatus", HttpStatus.BAD_REQUEST);
    }
}
