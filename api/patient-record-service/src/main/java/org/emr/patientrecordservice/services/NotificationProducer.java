package org.emr.patientrecordservice.services;

import org.emr.patientrecordservice.models.ProviderNotification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendProviderNotification(ProviderNotification providerNotification) {
        rabbitTemplate.convertAndSend("providerExchange","provider.notification", providerNotification);
    }

}
