package com.stage.gestionnoteback.dtos;


import lombok.Data;

import java.util.Date;

@Data
public class DepenseDTO {
    private Long id;
    private Date dateDepense;
    private String client;
    private String produit_Projet;
    private String typeI;
    private String typeD;
    private double montant;
    private String status = "En cours";
    private String username;
}
