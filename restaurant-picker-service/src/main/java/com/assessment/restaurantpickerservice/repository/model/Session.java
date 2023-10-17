package com.assessment.restaurantpickerservice.repository.model;

import com.assessment.restaurantpickerservice.util.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

import static com.assessment.restaurantpickerservice.util.Constants.SESSION_ACTIVE;

@Entity
@Table(name = "session")
@Data
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String sessionId;

    @Column(nullable = false)
    private String initiator;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date creationTime;

    @Column
    private String status;

    @PrePersist
    protected void onCreate() {
        creationTime = new Date();
        status = SESSION_ACTIVE;
    }

}
