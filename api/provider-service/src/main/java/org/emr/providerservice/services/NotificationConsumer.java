package org.emr.providerservice.services;


import org.emr.providerservice.models.HealthcareProvider;
import org.emr.providerservice.models.ProviderNotification;
import org.emr.providerservice.models.Notification;
import org.emr.providerservice.repos.HealthcareProviderRepository;
import org.emr.providerservice.repos.NotificationRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private HealthcareProviderRepository healthcareProviderRepository;

    @RabbitListener(queues = "providerNotificationQueue")
    public void receiveNotification(ProviderNotification providerNotification) {
        try {
            HealthcareProvider healthcareProvider = healthcareProviderRepository.findById(providerNotification.getProviderId()).orElse(null);
            if (healthcareProvider != null) {
                Notification notification = new Notification(healthcareProvider, providerNotification.getType(), providerNotification.getMessage(),false);
                Notification savedNotification = notificationRepository.save(notification);
                System.out.println("New Notification "+savedNotification);
            }
            else{
                System.out.println("No healthcare provider found");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error with Sending notification");
        }

    }
}
