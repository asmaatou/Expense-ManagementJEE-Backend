package com.stage.gestionnoteback.web;

import com.stage.gestionnoteback.dtos.DepenseDTO;
import com.stage.gestionnoteback.dtos.EmployeDTO;
import com.stage.gestionnoteback.exceptions.DepenseNotFoundException;
import com.stage.gestionnoteback.services.GestionNoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class DepenseRestAPI {
    private GestionNoteService gestionNoteService;
    public DepenseRestAPI(GestionNoteService gestionNoteService){
        this.gestionNoteService=gestionNoteService;
    }
    @GetMapping("/depenses")
    public List<DepenseDTO> depenses(){
        return gestionNoteService.listDepenses();
    }
    @GetMapping("/depenses/{id}")
    public DepenseDTO getDepense(@PathVariable(name = "id") Long depenseId) throws DepenseNotFoundException {
        return gestionNoteService.getDepense(depenseId);
    }
}
