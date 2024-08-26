package com.example.pol.repository;

import com.example.pol.model.Defendant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DefendantRepository extends JpaRepository<Defendant, Long> {
}
