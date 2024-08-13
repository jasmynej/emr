package org.emr.billingservice.controllers;


import org.emr.billingservice.models.Invoice;
import org.emr.billingservice.models.NotificationDTO;
import org.emr.billingservice.repos.InvoiceRepo;
import org.emr.billingservice.services.NotificationProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/billing/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceRepo invoiceRepo;

    @Autowired
    private NotificationProducer notificationProducer;

    @GetMapping
    public List<Invoice> getInvoices() {
        return invoiceRepo.findAll();
    }

    @PostMapping("/create")
    public Invoice createInvoice(@RequestBody Invoice invoice) {
        Invoice savedInvoice = invoiceRepo.save(invoice);
        NotificationDTO notificationDTO = new NotificationDTO(invoice.getPatientId(),"invoice_created", "New Invoice Created "+invoice.getInvoiceId(),false);
        notificationProducer.sendNotification(notificationDTO);
        return savedInvoice;
    }

    @PostMapping("/{id}/pay")
    public Invoice payInvoice(@PathVariable String id) {
        Invoice invoice = invoiceRepo.findByInvoiceId(id);
        LocalDate today = LocalDate.now();
        invoice.setPaymentDate(today);
        invoice.setStatus("PAID");
        NotificationDTO notificationDTO = new NotificationDTO(invoice.getPatientId(),"invoice_paid","Invoice Paid "+invoice.getInvoiceId(),false);
        notificationProducer.sendNotification(notificationDTO);
        return invoiceRepo.save(invoice);
    }


}
