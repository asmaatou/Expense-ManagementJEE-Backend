package com.stage.gestionnoteback.repositories;

import com.stage.gestionnoteback.entities.Depense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepenseRepository extends JpaRepository<Depense, Long> {
}
