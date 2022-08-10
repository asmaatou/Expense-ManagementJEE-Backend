package com.stage.gestionnoteback.web;

import com.stage.gestionnoteback.dtos.EmployeDTO;
import com.stage.gestionnoteback.entities.Employe;
import com.stage.gestionnoteback.exceptions.EmployeNotFoundException;
import com.stage.gestionnoteback.services.GestionNoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class EmployeRestController {
    private GestionNoteService gestionNoteService;
    @GetMapping("/employes")
    public List<EmployeDTO> employes(){
        return gestionNoteService.listEmployes();
    }
    @GetMapping("/employes/{id}")
    public EmployeDTO getEmploye(@PathVariable(name = "id") Long employeId) throws EmployeNotFoundException {
        return gestionNoteService.getEmploye(employeId);
    }
    @PostMapping("/employes")
    public EmployeDTO saveEmploye(@RequestBody EmployeDTO employeDTO){
        return gestionNoteService.saveEmploye(employeDTO);
    }
    @PutMapping("/employes/{employeId}")
    public EmployeDTO updateEmploye(@PathVariable Long employeId,@RequestBody EmployeDTO employeDTO) {
        employeDTO.setId(employeId);
        return gestionNoteService.updateEmploye(employeDTO);
    }
    @DeleteMapping("/employes/{id}")
    public void deleteEmploye(@PathVariable Long id){
        gestionNoteService.deleteEmploye(id);
    }
}
