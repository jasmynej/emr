package org.emr.billingservice.controllers;


import org.emr.billingservice.models.Invoice;
import org.emr.billingservice.repos.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billing/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceRepo invoiceRepo;

    @GetMapping
    public List<Invoice> getInvoices() {
        return invoiceRepo.findAll();
    }

    @PostMapping("/create")
    public Invoice createInvoice(@RequestBody Invoice invoice) {
        return invoiceRepo.save(invoice);
    }
}
