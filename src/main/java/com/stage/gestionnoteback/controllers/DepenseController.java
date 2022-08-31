package com.stage.gestionnoteback.controllers;


import com.stage.gestionnoteback.dtos.DepenseDTO;
import com.stage.gestionnoteback.models.Depense;
import com.stage.gestionnoteback.models.User;
import com.stage.gestionnoteback.repository.DepenseRepository;
import com.stage.gestionnoteback.security.services.DepenseServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/dep")
public class DepenseController {

    @Autowired
    private DepenseServiceImpl depenseService;
    @Autowired
    private DepenseRepository depenseRepository;


    /*@GetMapping("/depense")
    @PreAuthorize("hasRole('EMPLOYE')")
    public List<DepenseDTO> Listdepense(){
        return  depenseService.listDepenses();
    }*/

    @PostMapping("/depense")
    @PreAuthorize("hasRole('EMPLOYE')")
    public DepenseDTO saveDepense(@RequestBody DepenseDTO depenseDTO){
        return depenseService.saveDepense(depenseDTO);
    }

    @PostMapping("/depense/{depenseId}")
    @PreAuthorize("hasRole('EMPLOYE')")
    public DepenseDTO updateDepense(@PathVariable Long depenseId,@RequestBody DepenseDTO depenseDTO){
        depenseDTO.setId(depenseId);
        return depenseService.updateDepense(depenseDTO);
    }

    @DeleteMapping("/depense/{id}")
    @PreAuthorize("hasRole('EMPLOYE')")
    public void deleteDepense(@PathVariable Long id){depenseService.deleteDepense(id);}

    @GetMapping("/depense")
    @PreAuthorize("hasRole('EMPLOYE')")
    public List<Depense> Listdepense(@RequestParam(name = "keyword") String keyword){
        return  depenseService.listDepense("%"+keyword+"%");
    }




    //////////////////////////////////////////////////////////////////////////////////


    @GetMapping("/depenses")
    @PreAuthorize("hasRole('MANAGER')")
    public List<DepenseDTO> Listdepense2(){
        return  depenseService.listDepenses();
    }

    @PostMapping("/acceptdep/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public Depense acceptDepense(@PathVariable Long id, @RequestBody Depense depense){
        return depenseService.accept(id,depense);
    }

    @PostMapping("/denydep/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public Depense denyDepense(@PathVariable Long id, @RequestBody Depense depense){
        return depenseService.Refuser(id,depense);
    }

    @GetMapping("/getDepense")
    @PreAuthorize("hasRole('MANAGER')")
    public List<Depense> getDepense(){
        String status = "In progress";
        List<Depense> depense = depenseRepository.findDepenseByStatus(status);
        return depense;
    }

    @GetMapping("/getAcceptedDepense")
    @PreAuthorize("hasRole('MANAGER')")
    public List<Depense> getAcceptedDepense(){
        String status = "Accepted";
        List<Depense> depense = depenseRepository.findDepenseByStatus(status);
        return depense;
    }

    @GetMapping("/getDeniedDepense")
    @PreAuthorize("hasRole('MANAGER')")
    public List<Depense> getDeniedDepense(){
        String status = "Denied";
        List<Depense> depense = depenseRepository.findDepenseByStatus(status);
        return depense;
    }

}