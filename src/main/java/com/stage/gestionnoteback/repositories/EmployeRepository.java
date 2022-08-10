package com.stage.gestionnoteback.repositories;

import com.stage.gestionnoteback.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeRepository extends JpaRepository<Employe,Long> {
    @Query("select e from Employe e where e.fullName like :kw")
    List<Employe> searchEmploye(@Param("kw") String keyword);
}
