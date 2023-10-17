package com.assessment.restaurantpickerservice.repository.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "invitations")
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String sessionId;

    @Column(nullable = false)
    private String userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date invitationTime;

    public Invitation(String sessionId, String userId) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.invitationTime = new Date();
    }

}

