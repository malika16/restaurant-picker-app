package com.assessment.restaurantpickerservice.repository.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "session_restaurant")
@Data
public class SessionRestaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String restaurantName;

    @Column(nullable = false)
    private String sessionId;

}

