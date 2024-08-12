package org.emr.patientservice.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LoginForm {
    private String lastName;
    private LocalDate birthDate;
}
