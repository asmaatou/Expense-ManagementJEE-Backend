package com.stage.gestionnoteback.repositories;

import com.stage.gestionnoteback.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager,Long> {
    @Query("select e from Manager e where e.fullName like :kw")
    List<Manager> searchManager(@Param("kw") String keyword);
}
