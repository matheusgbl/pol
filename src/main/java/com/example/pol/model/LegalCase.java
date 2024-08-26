package com.example.pol.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "legal_case")
public class LegalCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String caseNumber;

    @OneToMany(mappedBy = "legalCase", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Defendant> defendants = new HashSet<>();

    // Default constructor
    public LegalCase() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public Set<Defendant> getDefendants() {
        return defendants;
    }

    public void setDefendants(Set<Defendant> defendants) {
        this.defendants = defendants;
    }

    public void addDefendant(Defendant defendant) {
        defendants.add(defendant);
        defendant.setLegalCase(this);
    }
}
