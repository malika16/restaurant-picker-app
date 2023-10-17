package com.assessment.restaurantpickerservice.controller;

import com.assessment.model.SessionCreationResponse;
import com.assessment.model.SessionDetail;
import com.assessment.model.RestaurantListResponse;
import com.assessment.restaurantpickerservice.service.RestaurantPickerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestaurantPickerController.class)
public class RestaurantPickerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantPickerService restaurantPickerService;

    private static final String COMMA = ",";
    private static final String SAMPLE_SESSION_ID = "sampleSessionId";
    private static final String SAMPLE_USER_IDS = "user1,user2";
    private static final String SAMPLE_RESTAURANT_NAME = "SampleRestaurant";
    private static final String SAMPLE_PICKED_RESTAURANT = "PickedRestaurant";

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new RestaurantPickerController(restaurantPickerService)).build();
    }

    @Test
    void testCreateSession() throws Exception {
        SessionDetail sessionDetail = new SessionDetail();
        SessionCreationResponse sessionCreationResponse = new SessionCreationResponse();
        sessionCreationResponse.setSessionId(SAMPLE_SESSION_ID);
        when(restaurantPickerService.createSession(sessionDetail))
                .thenReturn(sessionCreationResponse);
        MvcResult mvcResult = mockMvc.perform(post("/sessions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andDo(print())
                .andReturn();

        this.mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.sessionId").value(SAMPLE_SESSION_ID));
    }

    @Test
    void testInviteUsers() throws Exception {
        Mockito.doNothing().when(restaurantPickerService).sendInvitations(SAMPLE_SESSION_ID, Arrays.asList(SAMPLE_USER_IDS.split(COMMA)));

        mockMvc.perform(post("/sessions/{sessionId}/invite?userIds={userIds}", SAMPLE_SESSION_ID, SAMPLE_USER_IDS))
                .andExpect(status().isOk());
    }

    @Test
    void testInviteUsersBadRequest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/sessions/{sessionId}/invite?userIds={userIds}", SAMPLE_SESSION_ID, null)).andReturn();

        this.mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testRestaurants() throws Exception {
        Mockito.doNothing().when(restaurantPickerService).submitRestaurantChoice(SAMPLE_SESSION_ID, SAMPLE_RESTAURANT_NAME);

        mockMvc.perform(post("/sessions/{sessionId}/restaurants", SAMPLE_SESSION_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"restaurantName\":\"" + SAMPLE_RESTAURANT_NAME + "\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testSubmittedRestaurants() throws Exception {
        List<String> submittedRestaurants = Arrays.asList("Restaurant1", "Restaurant2");
        RestaurantListResponse response = new RestaurantListResponse();
        response.setRestaurant(submittedRestaurants);
        when(restaurantPickerService.getSubmittedRestaurants(SAMPLE_SESSION_ID))
                .thenReturn(submittedRestaurants);
        MvcResult mvcResult = mockMvc.perform(get("/sessions/{sessionId}/restaurants", SAMPLE_SESSION_ID))
                .andExpect(status().isOk())
                .andDo(print()).andReturn();
        assertTrue(mvcResult.getAsyncResult().toString().contains("Restaurant1, Restaurant2"), "Controller returns the Expected Result");
    }

    @Test
    void testPickRestaurant() throws Exception {
        SessionDetail sessionDetail = new SessionDetail();
        when(restaurantPickerService.pickRandomRestaurant(SAMPLE_SESSION_ID, sessionDetail))
                .thenReturn(SAMPLE_PICKED_RESTAURANT);
        MvcResult mvcResult = mockMvc.perform(post("/sessions/{sessionId}/pickRestaurant", SAMPLE_SESSION_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getAsyncResult().toString().contains(SAMPLE_PICKED_RESTAURANT), "Controller returns the Expected Result");
    }
}
