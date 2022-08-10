package com.stage.gestionnoteback.services;

import com.stage.gestionnoteback.dtos.AdminDTO;
import com.stage.gestionnoteback.dtos.DepenseDTO;
import com.stage.gestionnoteback.dtos.EmployeDTO;
import com.stage.gestionnoteback.dtos.ManagerDTO;
import com.stage.gestionnoteback.entities.Admin;
import com.stage.gestionnoteback.entities.Depense;
import com.stage.gestionnoteback.entities.Employe;
import com.stage.gestionnoteback.entities.Manager;
import com.stage.gestionnoteback.enums.DepenseType;
import com.stage.gestionnoteback.enums.InventionType;
import com.stage.gestionnoteback.exceptions.AdminNotFoundException;
import com.stage.gestionnoteback.exceptions.DepenseNotFoundException;
import com.stage.gestionnoteback.exceptions.EmployeNotFoundException;
import com.stage.gestionnoteback.exceptions.ManagerNotFoundException;
import com.stage.gestionnoteback.mapppers.GestionNoteMapperImpl;
import com.stage.gestionnoteback.repositories.AdminRepository;
import com.stage.gestionnoteback.repositories.DepenseRepository;
import com.stage.gestionnoteback.repositories.EmployeRepository;
import com.stage.gestionnoteback.repositories.ManagerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class GestionNoteServiceImpl implements GestionNoteService{
    private DepenseRepository depenseRepository;
    private AdminRepository adminRepository;
    private ManagerRepository managerRepository;
    private EmployeRepository employeRepository;
    private GestionNoteMapperImpl dtoMapper;

    @Override
    public EmployeDTO saveEmploye(EmployeDTO employeDTO) {
        log.info("Saving new Employe");
        Employe employe =dtoMapper.fromEmployeDTO(employeDTO);
        Employe savedEmploye = employeRepository.save(employe);
        return dtoMapper.fromEmploye(savedEmploye);
    }
    @Override
    public List<EmployeDTO> listEmployes() {
        List<Employe> employes = employeRepository.findAll();
        List<EmployeDTO> employeDTOS = employes.stream()
                .map(employe -> dtoMapper.fromEmploye(employe))
                .collect(Collectors.toList());
        return employeDTOS;
    }
    @Override
    public  EmployeDTO getEmploye(Long employeId) throws EmployeNotFoundException {
        Employe employe = employeRepository.findById(employeId)
                .orElseThrow(() -> new EmployeNotFoundException("Employe not found"));
        return dtoMapper.fromEmploye(employe);
    }
    @Override
    public EmployeDTO updateEmploye(EmployeDTO employeDTO){
        log.info("Saving new employe");
        Employe employe=dtoMapper.fromEmployeDTO(employeDTO);
        Employe savedEmploye = employeRepository.save(employe);
        return dtoMapper.fromEmploye(savedEmploye);
    }

    @Override
    public  void deleteEmploye(Long employeId){
        employeRepository.deleteById(employeId);
    }
    @Override
    public List<EmployeDTO> searchEmployes(String keyword){
        List<Employe> employes=employeRepository.searchEmploye(keyword);
        List<EmployeDTO> employeDTOS =employes.stream().map(employe -> dtoMapper.fromEmploye(employe)).collect(Collectors.toList());
        return employeDTOS;
    }
    @Override
    public AdminDTO saveAdmin(AdminDTO AdminDTO) {
        log.info("Saving new Admin");
        Admin Admin =dtoMapper.fromAdminDTO(AdminDTO);
        Admin savedAdmin = adminRepository.save(Admin);
        return dtoMapper.fromAdmin(savedAdmin);
    }
    @Override
    public List<AdminDTO> listAdmins() {
        List<Admin> Admins = adminRepository.findAll();
        List<AdminDTO> AdminDTOS = Admins.stream()
                .map(Admin -> dtoMapper.fromAdmin(Admin))
                .collect(Collectors.toList());
        return AdminDTOS;
    }
    @Override
    public  AdminDTO getAdmin(Long AdminId) throws AdminNotFoundException {
        Admin Admin = adminRepository.findById(AdminId)
                .orElseThrow(() -> new AdminNotFoundException("Admin not found"));
        return dtoMapper.fromAdmin(Admin);
    }
    @Override
    public AdminDTO updateAdmin(AdminDTO AdminDTO){
        log.info("Saving new Admin");
        Admin Admin=dtoMapper.fromAdminDTO(AdminDTO);
        Admin savedAdmin = adminRepository.save(Admin);
        return dtoMapper.fromAdmin(savedAdmin);
    }

    @Override
    public  void deleteAdmin(Long AdminId){
        adminRepository.deleteById(AdminId);
    }
    @Override
    public List<AdminDTO> searchAdmins(String keyword){
        List<Admin> Admins=adminRepository.searchAdmin(keyword);
        List<AdminDTO> AdminDTOS =Admins.stream().map(Admin -> dtoMapper.fromAdmin(Admin)).collect(Collectors.toList());
        return AdminDTOS;
    }
    @Override
    public ManagerDTO saveManager(ManagerDTO managerDTO) {
        log.info("Saving new Manager");
        Manager Manager =dtoMapper.fromManagerDTO(managerDTO);
        Manager savedManager = managerRepository.save(Manager);
        return dtoMapper.fromManager(savedManager);
    }
    @Override
    public List<ManagerDTO> listManagers() {
        List<Manager> Managers = managerRepository.findAll();
        List<ManagerDTO> ManagerDTOS = Managers.stream()
                .map(Manager -> dtoMapper.fromManager(Manager))
                .collect(Collectors.toList());
        return ManagerDTOS;
    }
    @Override
    public  ManagerDTO getManager(Long ManagerId) throws ManagerNotFoundException {
        Manager Manager = managerRepository.findById(ManagerId)
                .orElseThrow(() -> new ManagerNotFoundException("Manager not found"));
        return dtoMapper.fromManager(Manager);
    }
    @Override
    public ManagerDTO updateManager(ManagerDTO ManagerDTO){
        log.info("Saving new Manager");
        Manager Manager=dtoMapper.fromManagerDTO(ManagerDTO);
        Manager savedManager = managerRepository.save(Manager);
        return dtoMapper.fromManager(savedManager);
    }

    @Override
    public  void deleteManager(Long ManagerId){
        managerRepository.deleteById(ManagerId);
    }
    @Override
    public List<ManagerDTO> searchManagers(String keyword){
        List<Manager> Managers=managerRepository.searchManager(keyword);
        List<ManagerDTO> ManagerDTOS =Managers.stream().map(Manager -> dtoMapper.fromManager(Manager)).collect(Collectors.toList());
        return ManagerDTOS;
    }

    @Override
    public DepenseDTO saveDepense(String client, String prodProj, InventionType inventionType, DepenseType depenseType, Double montant, String status, Long employId) throws EmployeNotFoundException {
        Employe employe=employeRepository.findById(employId).orElse(null);
        if (employe==null)
            throw new EmployeNotFoundException("Employe not found");
        Depense depense=new Depense();
        depense.setDateDepense(new Date());
        depense.setClient(client);
        depense.setProduit_Projet(prodProj);
        depense.setTypeI(InventionType.valueOf(String.valueOf(inventionType)));
        depense.setTypeD(DepenseType.valueOf(String.valueOf(depenseType)));
        depense.setMontant(montant);
        depense.getStatus();
        depense.setEmploye(employe);
        Depense savedDepense = depenseRepository.save(depense);
        return dtoMapper.fromDepense(savedDepense);
    }

    @Override
    public List<DepenseDTO> listDepenses() {
        List<Depense> depenses = depenseRepository.findAll();
        List<DepenseDTO> depenseDTOS = depenses.stream()
                .map(depense -> dtoMapper.fromDepense(depense))
                .collect(Collectors.toList());
        return depenseDTOS;
    }

    @Override
    public  DepenseDTO getDepense(Long depenseId) throws DepenseNotFoundException {
        Depense depense = depenseRepository.findById(depenseId)
                .orElseThrow(() -> new DepenseNotFoundException("Depense not found"));
        return dtoMapper.fromDepense(depense);
    }
}
