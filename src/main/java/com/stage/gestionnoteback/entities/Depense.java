package com.stage.gestionnoteback.entities;

import com.stage.gestionnoteback.enums.DepenseType;
import com.stage.gestionnoteback.enums.InventionType;
import com.stage.gestionnoteback.enums.StatusType;
import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.awt.*;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Depense {
    @Id
    private Long id;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateDepense;
    private String client;
    private String produit_Projet;
    @Enumerated(EnumType.STRING)
    private InventionType typeI;
    @Enumerated(EnumType.STRING)
    private DepenseType typeD;
    private Double montant;
    @Enumerated(EnumType.STRING)
    @Value("${Depense.status:En cours}")
    private StatusType status;
    @ManyToOne
    private Employe employe;


}
