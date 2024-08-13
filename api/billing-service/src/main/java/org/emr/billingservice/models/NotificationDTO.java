package org.emr.billingservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class NotificationDTO {
    private long patientId;
    private String type;
    private String message;
    private boolean isRead;



}
