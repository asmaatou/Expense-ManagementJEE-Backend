package com.stage.gestionnoteback.security.services;

import com.stage.gestionnoteback.dtos.DepenseDTO;
import com.stage.gestionnoteback.exceptions.DepenseNotFoundException;
import com.stage.gestionnoteback.models.Depense;

import java.util.List;

public interface DepenseService {

    List<DepenseDTO> searchDepense(String keyword);
    public DepenseDTO saveDepense(DepenseDTO depenseDTO);
    List<DepenseDTO> listDepenses();
    DepenseDTO getDepense(Long depenseId) throws DepenseNotFoundException;
    DepenseDTO updateDepense(DepenseDTO depenseDTO);
    void deleteDepense(Long depenseId);
    Depense accept(Long depenseId, Depense depense);
    Depense Refuser(Long depenseId, Depense depense);
    List<Depense> listDepense(String keyword);
}
