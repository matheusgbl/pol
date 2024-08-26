package com.example.pol.service;

import com.example.pol.model.LegalCase;
import com.example.pol.model.Defendant;
import com.example.pol.repository.LegalCaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class LegalCaseService {

    private final LegalCaseRepository legalCaseRepository;

    public LegalCaseService(LegalCaseRepository legalCaseRepository) {
        this.legalCaseRepository = legalCaseRepository;
    }


    public static class CaseAlreadyExistsException extends RuntimeException {
        public CaseAlreadyExistsException(String message) {
            super(message);
        }
    }

    @Transactional
    public void saveCase(String caseNumber) {
        if (legalCaseRepository.findByCaseNumber(caseNumber).isPresent()) {
            throw new CaseAlreadyExistsException("O processo de número " + caseNumber + " já existe.");
        }

        LegalCase caseEntity = new LegalCase();
        caseEntity.setCaseNumber(caseNumber);
        legalCaseRepository.save(caseEntity);
    }

    public List<LegalCase> getAllCases() {
        return legalCaseRepository.findAll();
    }

    public void deleteCase(Long caseId) {
        legalCaseRepository.deleteById(caseId);
    }

    @Transactional
    public LegalCase addDefendantsToCase(Long caseId, List<String> defendantNames) {
        LegalCase legalCase = legalCaseRepository.findById(caseId)
                .orElseThrow(() -> new IllegalArgumentException("Case with ID " + caseId + " not found"));

        for (String name : defendantNames) {
            Defendant defendant = new Defendant();
            defendant.setName(name);
            defendant.setLegalCase(legalCase);

            legalCase.addDefendant(defendant);  // Add defendant to the case
        }

        legalCaseRepository.save(legalCase); // Save the case with the new defendants

        return legalCase;
    }

    public LegalCase getCaseById(Long caseId) {
        return legalCaseRepository.findById(caseId)
                .orElseThrow(() -> new IllegalArgumentException("Processo de número " + caseId + " não encontrado."));
    }
}
