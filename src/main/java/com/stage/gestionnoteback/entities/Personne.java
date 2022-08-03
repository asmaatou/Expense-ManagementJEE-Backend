package com.stage.gestionnoteback.entities;

import com.stage.gestionnoteback.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String fullName;
    private String email;
    @Enumerated(EnumType.STRING)
    private Roles role;
}
