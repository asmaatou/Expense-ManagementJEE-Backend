package com.stage.gestionnoteback.security.services;


import com.stage.gestionnoteback.dtos.DepenseDTO;
import com.stage.gestionnoteback.exceptions.DepenseNotFoundException;
import com.stage.gestionnoteback.mappers.GestionNoteMapperImpl;
import com.stage.gestionnoteback.models.Depense;
import com.stage.gestionnoteback.repository.DepenseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class DepenseServiceImpl implements DepenseService{
    private GestionNoteMapperImpl dtoMapper;
    private DepenseRepository depenseRepository;



    @Override
    public DepenseDTO saveDepense(DepenseDTO depenseDTO) {
        Depense depense = dtoMapper.fromDepenseDTO(depenseDTO);
        Depense savedDepense = depenseRepository.save(depense);
        return dtoMapper.fromDepense(savedDepense);
    }

    @Override
    public List<DepenseDTO> listDepenses(){
        List<Depense> depenses = depenseRepository.findAll();
        List<DepenseDTO> depenseDTOS = depenses.stream()
                .map(depense -> dtoMapper.fromDepense(depense))
                .collect(Collectors.toList());
        return depenseDTOS;

    }

    @Override
    public DepenseDTO getDepense(Long depenseId) throws DepenseNotFoundException {
        Depense depense = depenseRepository.findById(depenseId)
                .orElseThrow(() -> new DepenseNotFoundException("Depense not found"));
        return dtoMapper.fromDepense(depense);
    }

    @Override
    public DepenseDTO updateDepense(DepenseDTO depenseDTO){
        Depense depense = dtoMapper.fromDepenseDTO(depenseDTO);
        Depense savedDepense = depenseRepository.save(depense);
        return dtoMapper.fromDepense(savedDepense);
    }

    @Override
    public void deleteDepense(Long depenseId){
        depenseRepository.deleteById(depenseId);
    }


    @Override
    public Depense accept(Long depenseId, Depense depense){
         depense = depenseRepository.findById(depenseId).get();
         depense.setStatus("Accepted");
         return depenseRepository.save(depense);
    }

    @Override
    public Depense Refuser(Long depenseId, Depense depense){
        depense = depenseRepository.findById(depenseId).get();
        depense.setStatus("Denied");
        return depenseRepository.save(depense);
    }

    @Override
    public List<Depense> listDepense(String keyword){
        List<Depense> depenses = depenseRepository.findDepenseByUsername(keyword);
        List<DepenseDTO> depenseDTOS = depenses.stream()
                .map(depense -> dtoMapper.fromDepense(depense))
                .collect(Collectors.toList());
       return depenses;

    }

}
