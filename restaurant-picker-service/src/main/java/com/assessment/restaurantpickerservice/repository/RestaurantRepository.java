package com.assessment.restaurantpickerservice.repository;

import com.assessment.restaurantpickerservice.repository.model.SessionRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<SessionRestaurant, String> {
    List<SessionRestaurant> findBySessionId(String sessionId);
}
