package com.stage.gestionnoteback.repositories;

import com.stage.gestionnoteback.entities.Employe;
import com.stage.gestionnoteback.entities.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonneRepository extends JpaRepository<Personne, Long> {

}
