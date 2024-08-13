package org.emr.billingservice.repos;

import org.emr.billingservice.models.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InvoiceRepo extends MongoRepository<Invoice, String> {
    Invoice findByInvoiceId(String invoiceId);
}
