package org.emr.patientservice.controllers;

import org.emr.patientservice.models.LoginForm;
import org.emr.patientservice.models.Patient;
import org.emr.patientservice.repos.PatientRepository;
import org.emr.patientservice.services.PatientProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientProducer patientProducer;

    @GetMapping("")
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @PostMapping("/create")
    public Patient createPatient(@RequestBody Patient patient) {
        Patient savedPatient = patientRepository.save(patient);
        patientProducer.sendNewPatient(savedPatient);
        return savedPatient;
    }

    @PostMapping("/create-batch")
    public List<Patient> createPatientBatch(@RequestBody List<Patient> patients) {
        return patientRepository.saveAll(patients);
    }

    @PostMapping("/send-patient/{id}")
    public String sendPatient(@PathVariable Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        patientProducer.sendNewPatient(patient);
        return "Sent "+ patient;
    }

    @PostMapping("/login")
    public ResponseEntity<Patient> login(@RequestBody LoginForm loginForm) {
        System.out.println(loginForm);
        Patient foundPatient = patientRepository.findByLastNameAndDateOfBirth(loginForm.getLastName(),loginForm.getBirthDate()).orElse(null);
        return new ResponseEntity<>(foundPatient, HttpStatus.OK) ;
    }
}
