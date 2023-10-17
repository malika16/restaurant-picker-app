package com.assessment.restaurantpickerservice.service;

import com.assessment.restaurantpickerservice.repository.InvitationRepository;
import com.assessment.restaurantpickerservice.repository.RestaurantRepository;
import com.assessment.restaurantpickerservice.repository.SessionRepository;
import com.assessment.restaurantpickerservice.repository.model.Invitation;
import com.assessment.restaurantpickerservice.repository.model.Session;
import com.assessment.restaurantpickerservice.repository.model.SessionRestaurant;
import com.assessment.restaurantpickerservice.validate.RestaurantPickerValidator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RestaurantPickerServiceTest {

    @InjectMocks
    private RestaurantPickerService restaurantPickerService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private InvitationRepository invitationRepository;

    @Mock
    private RestaurantPickerValidator restaurantPickerValidator;

    @Test
    public void testCreateSession() {
        com.assessment.model.SessionDetail sessionDetail = new com.assessment.model.SessionDetail();
        sessionDetail.setInitiator("Malika");

        Session session = new Session();
        session.setSessionId("test");
        session.setInitiator(sessionDetail.getInitiator());

        when(sessionRepository.save(any(Session.class))).thenReturn(session);

        com.assessment.model.SessionCreationResponse response = restaurantPickerService.createSession(sessionDetail);

        assertThat(response).isNotNull();
        assertThat(response.getSessionId()).isNotNull();
        assertThat(response.getSessionId()).isEqualTo("test");
    }

    @Test
    public void testSendInvitations() {
        String sessionId = "sampleSessionId";
        List<String> userIds = Arrays.asList("user1", "user2", "user3");

        Session session = new Session();
        when(sessionRepository.findBySessionId(any())).thenReturn(session);

        when(invitationRepository.save(any())).thenReturn(null);

        restaurantPickerService.sendInvitations(sessionId, userIds);
        Mockito.verify(sessionRepository, times(1)).findBySessionId(any());
        Mockito.verify(invitationRepository, times(3)).save(any());
    }

    @Test
    public void testSubmitRestaurantChoice() {
        String sessionId = "testSessionId";
        String restaurantName = "Restaurant1";

        Session session = new Session();
        when(sessionRepository.findBySessionId(any())).thenReturn(session);
        when(restaurantRepository.save(any())).thenReturn(null);
        restaurantPickerService.submitRestaurantChoice(sessionId, restaurantName);
        Mockito.verify(sessionRepository, times(1)).findBySessionId(any());
        Mockito.verify(restaurantRepository, times(1)).save(any());
    }

    @Test
    public void testGetSubmittedRestaurants() {
        String sessionId = "testSessionId";

        Session session = new Session();
        when(sessionRepository.findBySessionId(sessionId)).thenReturn(session);

        when(restaurantRepository.findBySessionId(sessionId)).thenReturn(Arrays.asList(
                getRestaurant("Restaurant1"),
                getRestaurant("Restaurant2")
        ));

        List<String> submittedRestaurants = restaurantPickerService.getSubmittedRestaurants(sessionId);

        assertThat(submittedRestaurants).isNotNull();
        assertThat(submittedRestaurants).containsExactly("Restaurant1", "Restaurant2");
    }



    @Test
    public void testPickRandomRestaurant() {
        String sessionId = "testSessionId";
        com.assessment.model.SessionDetail sessionDetail = new com.assessment.model.SessionDetail();
        sessionDetail.setInitiator("Malika");

        Session session = new Session();
        when(sessionRepository.findBySessionId(any())).thenReturn(session);

        when(restaurantRepository.findBySessionId(any())).thenReturn(Arrays.asList(
                getRestaurant("Restaurant1"),
                getRestaurant("Restaurant2")
        ));

        String randomRestaurant = restaurantPickerService.pickRandomRestaurant(sessionId, sessionDetail);

        assertThat(randomRestaurant).isNotNull();
    }

    private SessionRestaurant getRestaurant(String restaurant1) {
        SessionRestaurant sessionRestaurant = new SessionRestaurant();
        sessionRestaurant.setRestaurantName(restaurant1);
        return sessionRestaurant;
    }
}
