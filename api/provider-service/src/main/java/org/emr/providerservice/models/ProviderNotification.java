package org.emr.providerservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProviderNotification {
    private long providerId;
    private String type;
    private String message;
}
