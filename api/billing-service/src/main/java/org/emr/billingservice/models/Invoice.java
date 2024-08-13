package org.emr.billingservice.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Document(collection = "invoices")
@Data
@NoArgsConstructor
public class Invoice {
    @Id
    private String id;
    private String invoiceId;
    private Long patientId;
    private Long providerId;
    private BigDecimal amount;
    private String status;
    private LocalDate dueDate;
    private LocalDate paymentDate;



}
