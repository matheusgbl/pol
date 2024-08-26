package com.example.pol.controller;

import com.example.pol.dto.DefendantRequest;
import com.example.pol.dto.LegalCaseRequest;
import com.example.pol.model.LegalCase;
import com.example.pol.service.LegalCaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cases")
public class LegalCaseController {

    private final LegalCaseService legalCaseService;

    public LegalCaseController(LegalCaseService legalCaseService) {
        this.legalCaseService = legalCaseService;
    }

    public static class CaseAlreadyExistsException extends RuntimeException {
        public CaseAlreadyExistsException(String message) {
            super(message);
        }
    }


    @PostMapping
    public ResponseEntity<String> saveCase(@RequestBody Map<String, Object> jsonRequest) {
        if (!jsonRequest.containsKey("caseNumber")) {
            return new ResponseEntity<>("Tipo inválido, o cadastro deve ser exatamente como o exemplo: {\"caseNumber\": 12535}.", HttpStatus.BAD_REQUEST);
        }

        try {
            int caseNumber = (Integer) jsonRequest.get("caseNumber");
            legalCaseService.saveCase(String.valueOf(caseNumber));
            return new ResponseEntity<>("Processo criado com êxito.", HttpStatus.CREATED);
        } catch (CaseAlreadyExistsException ex) {
            return new ResponseEntity<>("Processo de número " + jsonRequest.get("caseNumber") + " não existe.", HttpStatus.BAD_REQUEST);
        } catch (ClassCastException ex) {
            return new ResponseEntity<>("'caseNumber' possui valor inválido. Deve ser um número inteiro.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<LegalCase>> getAllCases() {
        return ResponseEntity.ok(legalCaseService.getAllCases());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LegalCase> getCaseById(@PathVariable Long id) {
        try {
            LegalCase legalCase = legalCaseService.getCaseById(id);
            return new ResponseEntity<>(legalCase, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCase(@PathVariable Long id) {
        legalCaseService.deleteCase(id);
        return new ResponseEntity<>("Processo excluído com sucesso.", HttpStatus.OK);
    }

    @PostMapping("/{id}/defendants")
    public ResponseEntity<LegalCase> addDefendants(@PathVariable Long id, @RequestBody DefendantRequest defendantRequest) {
        try {
            LegalCase updatedCase = legalCaseService.addDefendantsToCase(id, defendantRequest.getNames());
            return ResponseEntity.ok(updatedCase);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
