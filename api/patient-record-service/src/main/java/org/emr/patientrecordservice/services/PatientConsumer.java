package org.emr.patientrecordservice.services;


import org.emr.patientrecordservice.configs.RabbitMQConfig;
import org.emr.patientrecordservice.models.PatientDTO;
import org.emr.patientrecordservice.models.PatientRecord;
import org.emr.patientrecordservice.repos.PatientRecordRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientConsumer {

    @Autowired
    private PatientRecordRepository patientRecordRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void getSentPatient(PatientDTO patientDTO) {
        PatientRecord patientRecord = new PatientRecord();
        patientRecord.setPatientId(patientDTO.getId());
        patientRecordRepository.save(patientRecord);
        System.out.println(patientRecord);
    }
}
