package org.emr.billingservice.services;

import org.emr.billingservice.models.NotificationDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendNotification(NotificationDTO notificationDTO) {
        rabbitTemplate.convertAndSend("patientExchange","patient.notification",notificationDTO);
        System.out.println("notification sent" + notificationDTO);
    }
}
