package com.stage.gestionnoteback.repository;

import com.stage.gestionnoteback.models.ERole;
import com.stage.gestionnoteback.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
