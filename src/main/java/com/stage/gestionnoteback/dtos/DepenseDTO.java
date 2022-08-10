package com.stage.gestionnoteback.dtos;

import com.stage.gestionnoteback.enums.DepenseType;
import com.stage.gestionnoteback.enums.InventionType;
import lombok.Data;

import java.util.Date;
@Data
public class DepenseDTO {
    private Long id;
    private Date dateDepense;
    private String client;
    private String produit_Projet;
    private InventionType typeI;
    private DepenseType typeD;
    private Double montant;
    private String status;
   private EmployeDTO employeDTO;
}
