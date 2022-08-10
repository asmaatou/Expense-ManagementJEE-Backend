package com.stage.gestionnoteback.services;

import com.stage.gestionnoteback.dtos.EmployeDTO;
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
    EmployeDTO saveEmploye(EmployeDTO employeDTO);
    Depense saveDepense(String client, String prodProj, InventionType inventionType, DepenseType depenseType, Double montant, Long employId) throws EmployeNotFoundException;

//    List<Personne> listPersonnes();
    List<EmployeDTO> listEmployes();
    Depense getDepense(Long depenseId) throws DepenseNotFoundException;

    EmployeDTO getEmploye(Long employeId) throws EmployeNotFoundException;

    EmployeDTO updateEmploye(EmployeDTO employeDTO);

    void deleteEmploye(Long employeId);

    void accepter();
    void refuser();
}
