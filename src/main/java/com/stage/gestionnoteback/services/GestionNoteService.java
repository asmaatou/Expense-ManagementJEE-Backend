package com.stage.gestionnoteback.services;

import com.stage.gestionnoteback.dtos.AdminDTO;
import com.stage.gestionnoteback.dtos.DepenseDTO;
import com.stage.gestionnoteback.dtos.EmployeDTO;
import com.stage.gestionnoteback.dtos.ManagerDTO;
import com.stage.gestionnoteback.enums.DepenseType;
import com.stage.gestionnoteback.enums.InventionType;
import com.stage.gestionnoteback.exceptions.AdminNotFoundException;
import com.stage.gestionnoteback.exceptions.DepenseNotFoundException;
import com.stage.gestionnoteback.exceptions.EmployeNotFoundException;
import com.stage.gestionnoteback.exceptions.ManagerNotFoundException;

import java.util.List;

public interface GestionNoteService {
    EmployeDTO saveEmploye(EmployeDTO employeDTO);

    List<EmployeDTO> searchEmployes(String keyword);
    List<EmployeDTO> listEmployes();
    EmployeDTO getEmploye(Long employeId) throws EmployeNotFoundException;
    EmployeDTO updateEmploye(EmployeDTO employeDTO);
    void deleteEmploye(Long employeId);

    AdminDTO saveAdmin(AdminDTO AdminDTO);

    List<AdminDTO> listAdmins();

    AdminDTO getAdmin(Long AdminId) throws AdminNotFoundException;

    AdminDTO updateAdmin(AdminDTO AdminDTO);

    void deleteAdmin(Long AdminId);

    List<AdminDTO> searchAdmins(String keyword);

    ManagerDTO saveManager(ManagerDTO managerDTO);

    List<ManagerDTO> listManagers();

    ManagerDTO getManager(Long ManagerId) throws ManagerNotFoundException;

    ManagerDTO updateManager(ManagerDTO ManagerDTO);

    void deleteManager(Long ManagerId);

    List<ManagerDTO> searchManagers(String keyword);

    DepenseDTO saveDepense(String client, String prodProj, InventionType inventionType, DepenseType depenseType, Double montant, String status, Long employId) throws EmployeNotFoundException;
    List<DepenseDTO> listDepenses();
    DepenseDTO getDepense(Long depenseId) throws DepenseNotFoundException;


}
