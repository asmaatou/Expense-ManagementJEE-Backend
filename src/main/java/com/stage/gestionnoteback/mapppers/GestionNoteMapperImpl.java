package com.stage.gestionnoteback.mapppers;

import com.stage.gestionnoteback.dtos.AdminDTO;
import com.stage.gestionnoteback.dtos.DepenseDTO;
import com.stage.gestionnoteback.dtos.EmployeDTO;
import com.stage.gestionnoteback.dtos.ManagerDTO;
import com.stage.gestionnoteback.entities.Admin;
import com.stage.gestionnoteback.entities.Depense;
import com.stage.gestionnoteback.entities.Employe;
import com.stage.gestionnoteback.entities.Manager;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class GestionNoteMapperImpl {
    public EmployeDTO fromEmploye(Employe employe){
        EmployeDTO employeDTO = new EmployeDTO();
        BeanUtils.copyProperties(employe,employeDTO);
        return employeDTO;
    }
    public Employe fromEmployeDTO(EmployeDTO employeDTO){
        Employe employe = new Employe();
        BeanUtils.copyProperties(employeDTO,employe);
        return employe;
    }
    public AdminDTO fromAdmin(Admin admin){
        AdminDTO adminDTO = new AdminDTO();
        BeanUtils.copyProperties(admin,adminDTO);
        return adminDTO;
    }
    public Admin fromAdminDTO(AdminDTO adminDTO){
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminDTO,admin);
        return admin;
    }
    public ManagerDTO fromManager(Manager manager){
        ManagerDTO managerDTO = new ManagerDTO();
        BeanUtils.copyProperties(manager,managerDTO);
        return managerDTO;
    }
    public Manager fromManagerDTO(ManagerDTO managerDTO){
        Manager manager = new Manager();
        BeanUtils.copyProperties(managerDTO,manager);
        return manager;
    }
    public DepenseDTO fromDepense(Depense depense){
        DepenseDTO depenseDTO=new DepenseDTO();
        BeanUtils.copyProperties(depense,depenseDTO);
        depenseDTO.setEmployeDTO(fromEmploye(depense.getEmploye()));
        return depenseDTO;
    }
    public Depense fromDepenseDTO(DepenseDTO depenseDTO){
        Depense depense=new Depense();
        BeanUtils.copyProperties(depenseDTO,depense);
        depense.setEmploye(fromEmployeDTO(depenseDTO.getEmployeDTO()));
        return depense;
    }
}
