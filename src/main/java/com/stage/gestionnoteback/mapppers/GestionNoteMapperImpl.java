package com.stage.gestionnoteback.mapppers;

import com.stage.gestionnoteback.dtos.EmployeDTO;
import com.stage.gestionnoteback.entities.Employe;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class GestionNoteMapperImpl {
    public EmployeDTO fromEmploye(Employe employe){
        EmployeDTO employeDTO = new EmployeDTO();
        BeanUtils.copyProperties(employe,employeDTO);
        return  employeDTO;
    }
    public Employe fromEmployeDTO(EmployeDTO employeDTO){
        Employe employe = new Employe();
        BeanUtils.copyProperties(employeDTO,employe);
        return  employe;
    }
}
