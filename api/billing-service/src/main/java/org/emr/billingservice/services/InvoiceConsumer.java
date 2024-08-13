package org.emr.billingservice.services;

import org.emr.billingservice.models.Invoice;
import org.emr.billingservice.models.InvoiceRequest;
import org.emr.billingservice.models.NotificationDTO;
import org.emr.billingservice.repos.InvoiceRepo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class InvoiceConsumer {
    @Autowired
    private InvoiceRepo invoiceRepo;

    @Autowired
    private NotificationProducer notificationProducer;

    @RabbitListener(queues = "invoiceCreationQueue")
    public void createInvoice(InvoiceRequest invoiceRequest) {
        Invoice newInvoice = new Invoice();
        newInvoice.setPatientId(invoiceRequest.getPatientId());
        LocalDate today = LocalDate.now();
        LocalTime timeNow = LocalTime.now();
        newInvoice.setDueDate(today.plusDays(14));
        newInvoice.setProviderId(invoiceRequest.getProviderId());
        newInvoice.setStatus("NOT_PAID");
        newInvoice.setInvoiceId("INV-"+newInvoice.getPatientId()+today.getDayOfYear()+timeNow.getSecond());
        if(invoiceRequest.getInvoiceType().equals("visit")){
            newInvoice.setAmount(BigDecimal.valueOf(250.00));
        }
        else if(invoiceRequest.getInvoiceType().equals("lab")){
            newInvoice.setAmount(BigDecimal.valueOf(150.00));
        }
        else{
            newInvoice.setAmount(BigDecimal.valueOf(100.00));
        }
        Invoice savedInvoice = invoiceRepo.save(newInvoice);
        NotificationDTO notification = new NotificationDTO(invoiceRequest.getPatientId(),"invoice_created","New Invoice Created"+newInvoice.getInvoiceId(),false);
        notificationProducer.sendNotification(notification);
        System.out.println(savedInvoice);


    }
}
