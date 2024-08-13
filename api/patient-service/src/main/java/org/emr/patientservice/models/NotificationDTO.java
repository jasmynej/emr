package org.emr.patientservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationDTO {
    private long patientId;
    private String type;
    private String message;
    private boolean isRead;



}

