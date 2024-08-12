package org.emr.providerservice.controllers;



import org.emr.providerservice.models.HealthcareProvider;
import org.emr.providerservice.models.LoginForm;
import org.emr.providerservice.repos.HealthcareProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/healthcare-providers")
public class HealthcareProviderController {
    @Autowired
    private HealthcareProviderRepository healthcareProviderRepoisitory;

    @GetMapping
    public List<HealthcareProvider> getAllProviders() {
        return healthcareProviderRepoisitory.findAll();
    }

    @GetMapping("/{id}")
    public HealthcareProvider getProviderById(@PathVariable Long id) {
        return healthcareProviderRepoisitory.findById(id).orElse(null);
    }

    @PostMapping("/create")
    public HealthcareProvider createProvider(@RequestBody HealthcareProvider provider) {
        return healthcareProviderRepoisitory.save(provider);
    }

    @PostMapping("/create-batch")
    public List<HealthcareProvider> createProviderBatch(@RequestBody List<HealthcareProvider> providers) {
        return healthcareProviderRepoisitory.saveAll(providers);
    }

    @PostMapping("/login")
    public HealthcareProvider login(@RequestBody LoginForm loginForm) {
        System.out.println(loginForm);
        HealthcareProvider provider = healthcareProviderRepoisitory.findByEmail(loginForm.getEmail()).orElse(null);
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
}
