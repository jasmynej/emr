package org.emr.providerservice.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name="provider_id",referencedColumnName = "id")
    @JsonIgnore
    private HealthcareProvider provider;

    private String type;
    private String message;
    private boolean isRead;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @CreationTimestamp
    private LocalDateTime updatedAt;

    public Notification(HealthcareProvider healthcareProvider, String type, String message, boolean b) {
        this.provider = healthcareProvider;
        this.type = type;
        this.message = message;
        this.isRead = b;
    }
}
