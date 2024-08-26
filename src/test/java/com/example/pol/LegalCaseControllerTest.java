package com.example.pol;

import com.example.pol.controller.LegalCaseController;
import com.example.pol.model.LegalCase;
import com.example.pol.service.LegalCaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@WebMvcTest(LegalCaseController.class)
public class LegalCaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LegalCaseService legalCaseService;

    @Test
    public void testAddDefendants() throws Exception {

        when(legalCaseService.addDefendantsToCase(1L, Arrays.asList("John Doe", "Jane Smith"))).thenReturn(
                new LegalCase()
        );

        String jsonContent = "{\"names\": [\"John Doe\", \"Jane Smith\"]}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cases/1/defendants")
                        .contentType("application/json")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
