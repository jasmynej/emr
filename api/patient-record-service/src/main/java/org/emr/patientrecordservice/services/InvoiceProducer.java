package org.emr.patientrecordservice.services;

import org.emr.patientrecordservice.models.NewInvoice;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class InvoiceProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendInvoiceRequest(NewInvoice newInvoice){
        rabbitTemplate.convertAndSend("patientExchange","patient.invoice",newInvoice);
        System.out.println("Invoice sent "+newInvoice);
    }
}
