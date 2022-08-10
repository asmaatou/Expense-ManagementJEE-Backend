package com.stage.gestionnoteback.web;


import com.stage.gestionnoteback.dtos.ManagerDTO;
import com.stage.gestionnoteback.exceptions.ManagerNotFoundException;
import com.stage.gestionnoteback.services.GestionNoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class ManagerRestController {
    private GestionNoteService gestionNoteService;
    @GetMapping("/managers")
    public List<ManagerDTO> managers(){
        return gestionNoteService.listManagers();
    }
    @GetMapping("/managers/search")
    public  List<ManagerDTO> searchManagers(@RequestParam(name = "keyword",defaultValue = "")String keyword){
        return gestionNoteService.searchManagers("%"+keyword+"%");
    }
    @GetMapping("/managers/{id}")
    public ManagerDTO getManager(@PathVariable(name = "id") Long managerId) throws ManagerNotFoundException {
        return gestionNoteService.getManager(managerId);
    }
    @PostMapping("/managers")
    public ManagerDTO saveManager(@RequestBody ManagerDTO managerDTO){
        return gestionNoteService.saveManager(managerDTO);
    }
    @PutMapping("/managers/{managerId}")
    public ManagerDTO updateManager(@PathVariable Long managerId,@RequestBody ManagerDTO managerDTO) {
        managerDTO.setId(managerId);
        return gestionNoteService.updateManager(managerDTO);
    }
    @DeleteMapping("/managers/{id}")
    public void deleteManager(@PathVariable Long id){
        gestionNoteService.deleteManager(id);
    }
}
