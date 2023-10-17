package com.assessment.restaurantpickerservice.repository;

import com.assessment.restaurantpickerservice.repository.model.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {
}
