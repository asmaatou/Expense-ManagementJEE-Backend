package com.stage.gestionnoteback.repositories;

import com.stage.gestionnoteback.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepository extends JpaRepository<Employe, Long> {
}
