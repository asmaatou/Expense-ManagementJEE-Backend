package com.stage.gestionnoteback.web;

import com.stage.gestionnoteback.dtos.AdminDTO;
import com.stage.gestionnoteback.exceptions.AdminNotFoundException;
import com.stage.gestionnoteback.services.GestionNoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class AdminRestController {
    private GestionNoteService gestionNoteService;
    @GetMapping("/admins")
    public List<AdminDTO> admins(){
        return gestionNoteService.listAdmins();
    }
    @GetMapping("/admins/search")
    public  List<AdminDTO> searchAdmins(@RequestParam(name = "keyword",defaultValue = "")String keyword){
        return gestionNoteService.searchAdmins("%"+keyword+"%");
    }
    @GetMapping("/admins/{id}")
    public AdminDTO getAdmin(@PathVariable(name = "id") Long adminId) throws AdminNotFoundException {
        return gestionNoteService.getAdmin(adminId);
    }
    @PostMapping("/admins")
    public AdminDTO saveAdmin(@RequestBody AdminDTO adminDTO){
        return gestionNoteService.saveAdmin(adminDTO);
    }
    @PutMapping("/admins/{adminId}")
    public AdminDTO updateAdmin(@PathVariable Long adminId,@RequestBody AdminDTO adminDTO) {
        adminDTO.setId(adminId);
        return gestionNoteService.updateAdmin(adminDTO);
    }
    @DeleteMapping("/admins/{id}")
    public void deleteAdmin(@PathVariable Long id){
        gestionNoteService.deleteAdmin(id);
    }
}
