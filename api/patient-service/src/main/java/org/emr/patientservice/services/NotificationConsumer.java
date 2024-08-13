package org.emr.patientservice.services;


import org.emr.patientservice.models.Notification;
import org.emr.patientservice.models.NotificationDTO;
import org.emr.patientservice.models.Patient;
import org.emr.patientservice.repos.NotificationRepository;
import org.emr.patientservice.repos.PatientRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private PatientRepository patientRepository;

    @RabbitListener(queues = "notificationQueue")
    public void getNewNotification(NotificationDTO notification) {
        try {
            Patient p = patientRepository.findById(notification.getPatientId()).orElse(null);
            if (p != null) {
                Notification savedNotification = notificationRepository.save(new Notification(
                        p,
                        notification.getType(),
                        notification.getMessage(),
                        notification.isRead()
                ));
                System.out.println("New Notification Received: " + savedNotification);
            } else {
                System.out.println("Patient not found for ID: " + notification.getPatientId());
            }
        } catch (Exception e) {
            // Log the error and handle it gracefully
            System.err.println("Error processing notification: " + e.getMessage());
            e.printStackTrace();
            // You might want to implement further handling like sending to a dead letter queue
        }
    }
}
