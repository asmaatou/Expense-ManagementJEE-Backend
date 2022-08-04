package com.stage.gestionnoteback.services;

import com.stage.gestionnoteback.entities.Depense;
import com.stage.gestionnoteback.entities.Employe;
import com.stage.gestionnoteback.entities.Personne;
import com.stage.gestionnoteback.enums.DepenseType;
import com.stage.gestionnoteback.enums.InventionType;
import com.stage.gestionnoteback.exceptions.DepenseNotFoundException;
import com.stage.gestionnoteback.exceptions.EmployeNotFoundException;
import com.stage.gestionnoteback.repositories.DepenseRepository;
import com.stage.gestionnoteback.repositories.EmployeRepository;
import com.stage.gestionnoteback.repositories.PersonneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class GestionNoteServiceImpl implements GestionNoteService{
    private PersonneRepository personneRepository;
    private DepenseRepository depenseRepository;
    private EmployeRepository employeRepository;
    /*@Override
    public Personne savePersonne(Personne personne) {
        log.info("Saving new Person");
        Personne savedPersonne = personneRepository.save(personne);
        return savedPersonne;
    }*/

    @Override
    public Employe saveEmploye(Employe employe) {
        log.info("Saving new Employe");
        Employe savedEmploye = employeRepository.save(employe);
        return savedEmploye;
    }

    @Override
    public Depense saveDepense(String client, String prodProj, InventionType inventionType, DepenseType depenseType, Double montant, Long employId) throws EmployeNotFoundException {
        Depense depense;
        Employe employe=employeRepository.findById(employId).orElse(null);
        if (employe==null)
            throw new EmployeNotFoundException("Employe not found");
        depense=new Depense();
        depense.setDateDepense(new Date());
        depense.setClient(client);
        depense.setProduit_Projet(prodProj);
        depense.setTypeI(InventionType.valueOf(String.valueOf(inventionType)));
        depense.setTypeD(DepenseType.valueOf(String.valueOf(depenseType)));
        depense.setMontant(montant);
        depense.setEmploye(employe);
        Depense savedDepense = depenseRepository.save(depense);
        return savedDepense;
    }

    /*@Override
    public List<Personne> listPersonnes() {
        return personneRepository.findAll();
    }*/

    @Override
    public List<Employe> listEmployes() {
        return employeRepository.findAll();
    }

    @Override
    public Depense getDepense(Long depenseId) throws DepenseNotFoundException {
        Depense depense=depenseRepository.findById(depenseId)
                .orElseThrow(()->new DepenseNotFoundException("Depense not found"));
        return depense;
    }

    @Override
    public void accepter() {

    }

    @Override
    public void refuser() {

    }
}
