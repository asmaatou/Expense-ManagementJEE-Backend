package com.stage.gestionnoteback.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stage.gestionnoteback.entities.Depense;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
public class EmployeDTO {
    private Long id;
    private String userName;
    private String fullName;
    private String email;
   }
