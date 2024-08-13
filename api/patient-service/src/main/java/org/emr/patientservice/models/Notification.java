package org.emr.patientservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="notifications")
@Data
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name="patient_id",referencedColumnName = "id")
    @JsonIgnore
    private Patient patient;

    private String type;
    private String message;
    private boolean isRead;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Notification(Patient patient, String type, String message, boolean isRead) {
        this.patient = patient;
        this.type = type;
        this.message = message;
        this.isRead = isRead;
    }
}
