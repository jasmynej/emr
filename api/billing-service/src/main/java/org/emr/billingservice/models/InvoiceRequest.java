package org.emr.billingservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvoiceRequest {
    private String invoiceType;
    private Long patientId;
    private Long providerId;
}
