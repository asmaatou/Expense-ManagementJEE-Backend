package com.stage.gestionnoteback.services;

import com.stage.gestionnoteback.entities.Depense;
import com.stage.gestionnoteback.entities.Employe;
import com.stage.gestionnoteback.entities.Personne;
import com.stage.gestionnoteback.enums.DepenseType;
import com.stage.gestionnoteback.enums.InventionType;
import com.stage.gestionnoteback.exceptions.DepenseNotFoundException;
import com.stage.gestionnoteback.exceptions.EmployeNotFoundException;

import java.util.Date;
import java.util.List;

public interface GestionNoteService {
//    Personne savePersonne(Personne personne);
    Employe saveEmploye(Employe employe);
    Depense saveDepense(String client, String prodProj, InventionType inventionType, DepenseType depenseType, Double montant, Long employId) throws EmployeNotFoundException;

//    List<Personne> listPersonnes();
    List<Employe> listEmployes();
    Depense getDepense(Long depenseId) throws DepenseNotFoundException;
    void accepter();
    void refuser();
}
