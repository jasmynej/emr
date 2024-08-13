package org.emr.providerservice.controllers;



import org.emr.providerservice.models.HealthcareProvider;
import org.emr.providerservice.models.LoginForm;
import org.emr.providerservice.models.Notification;
import org.emr.providerservice.models.Visit;
import org.emr.providerservice.repos.HealthcareProviderRepository;
import org.emr.providerservice.repos.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/healthcare-providers")
public class HealthcareProviderController {
    @Autowired
    private HealthcareProviderRepository healthcareProviderRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping
    public List<HealthcareProvider> getAllProviders() {
        return healthcareProviderRepository.findAll();
    }

    @GetMapping("/{id}")
    public HealthcareProvider getProviderById(@PathVariable Long id) {
        return healthcareProviderRepository.findById(id).orElse(null);
    }

    @PostMapping("/create")
    public HealthcareProvider createProvider(@RequestBody HealthcareProvider provider) {
        return healthcareProviderRepository.save(provider);
    }

    @PostMapping("/create-batch")
    public List<HealthcareProvider> createProviderBatch(@RequestBody List<HealthcareProvider> providers) {
        return healthcareProviderRepository.saveAll(providers);
    }

    @PostMapping("/login")
    public HealthcareProvider login(@RequestBody LoginForm loginForm) {
        System.out.println(loginForm);
        HealthcareProvider provider = healthcareProviderRepository.findByEmail(loginForm.getEmail()).orElse(null);
        if (provider == null) {
            return null;
        }
        else if (provider.getId() == loginForm.getId()) {
            return provider;
        }
        else{
            return null;
        }
    }

    @GetMapping("/{id}/visits")
    public List<Visit> getVisits(@PathVariable Long id) {
        Visit[] visits = restTemplate.getForObject("http://127.0.0.1:9140/api/patient-records/visits/" + id, Visit[].class);
        assert visits != null;
        return Arrays.asList(visits);
    }

    @PutMapping("/{id}/read")
    public Notification readNotification(@PathVariable Long id) {
        Notification notification = notificationRepository.findById(id).orElse(null);
        if (notification == null) {
            return null;
        }
        else{
            notification.setRead(true);
            return notificationRepository.save(notification);
        }
    }
}
