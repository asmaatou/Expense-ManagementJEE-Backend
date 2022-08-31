package com.stage.gestionnoteback.repository;


import com.stage.gestionnoteback.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
  @Query("SELECT u from User u LEFT JOIN u.roles role WHERE role.id = ?1")
  List<User> findUserByRole(int role);
  @Query("select u from User u where u.username like :kw")
  List<User> searchUser(@Param("kw") String username);
  Boolean existsByUsername(String username);
  Boolean existsByEmail(String email);
}
