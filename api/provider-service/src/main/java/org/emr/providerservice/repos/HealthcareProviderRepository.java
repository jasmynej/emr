package org.emr.providerservice.repos;

import org.emr.providerservice.models.HealthcareProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HealthcareProviderRepository extends JpaRepository<HealthcareProvider,Long> {
    Optional<HealthcareProvider> findByEmail(String email);
}
