package com.example.pol.repository;

import com.example.pol.model.LegalCase;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LegalCaseRepository extends JpaRepository<LegalCase, Long> {
    Optional<LegalCase> findByCaseNumber(String caseNumber);
}
