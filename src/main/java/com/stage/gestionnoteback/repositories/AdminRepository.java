package com.stage.gestionnoteback.repositories;

import com.stage.gestionnoteback.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    @Query("select e from Admin e where e.fullName like :kw")
    List<Admin> searchAdmin(@Param("kw") String keyword);
}
