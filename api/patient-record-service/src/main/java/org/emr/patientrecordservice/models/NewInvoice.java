package org.emr.patientrecordservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class NewInvoice {
    private String invoiceType;
    private Long patientId;
    private Long providerId;
}
