package com.stage.gestionnoteback.repository;

import com.stage.gestionnoteback.models.Depense;
import com.stage.gestionnoteback.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface DepenseRepository extends JpaRepository<Depense, Long> {
    @Query("SELECT d from Depense d WHERE d.status=?1")
    List<Depense> findDepenseByStatus(String status);
    @Query("SELECT d from Depense d where d.username like :kw")
    List<Depense> findDepenseByUsername(@Param("kw") String username);

}
