package com.assessment.restaurantpickerservice.repository;

import com.assessment.restaurantpickerservice.repository.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, String> {
    Session findBySessionId(String sessionId);
}
