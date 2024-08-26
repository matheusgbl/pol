package com.example.pol;

import com.example.pol.model.Defendant;
import com.example.pol.model.LegalCase;
import com.example.pol.repository.DefendantRepository;
import com.example.pol.repository.LegalCaseRepository;
import com.example.pol.service.LegalCaseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LegalCaseServiceTest {

    @Mock
    private LegalCaseRepository legalCaseRepository;

    @Mock
    private DefendantRepository defendantRepository;

    @InjectMocks
    private LegalCaseService legalCaseService;

    @Test
    @Transactional
    public void testAddDefendantsToCase() {
        Long caseId = 1L;
        LegalCase legalCase = new LegalCase();
        legalCase.setId(caseId);
        legalCase.setCaseNumber("12345");

        when(legalCaseRepository.findById(caseId)).thenReturn(Optional.of(legalCase));

        LegalCase updatedCase = legalCaseService.addDefendantsToCase(caseId, Arrays.asList("John Doe", "Jane Smith"));

        Set<Defendant> defendants = updatedCase.getDefendants(); // Get the set of defendants
        List<Defendant> defendantList = defendants.stream().toList(); // Convert to list

        assertEquals(2, defendantList.size());
        assertEquals("John Doe", defendantList.get(0).getName());
        assertEquals("Jane Smith", defendantList.get(1).getName());
    }
}
