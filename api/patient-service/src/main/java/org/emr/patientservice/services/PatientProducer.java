package org.emr.patientservice.services;

import org.emr.patientservice.configs.RabbitMQConfig;
import org.emr.patientservice.models.Patient;
import org.emr.patientservice.repos.PatientRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendNewPatient(Patient patient) {
        rabbitTemplate.convertAndSend("patientExchange","patient.create",patient);
        System.out.println("Patient Sent "+ patient);
    }

}
