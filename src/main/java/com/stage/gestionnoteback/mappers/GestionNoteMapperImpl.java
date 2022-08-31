package com.stage.gestionnoteback.mappers;

import com.stage.gestionnoteback.dtos.DepenseDTO;
import com.stage.gestionnoteback.dtos.UserDTO;
import com.stage.gestionnoteback.models.Depense;
import com.stage.gestionnoteback.models.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class GestionNoteMapperImpl {
    public UserDTO fromUser(User user){
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        return userDTO;
    }

    public  User fromUserDTO(UserDTO userDTO){
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        return user;
    }

    public DepenseDTO fromDepense(Depense depense){
        DepenseDTO depenseDTO=new DepenseDTO();
        BeanUtils.copyProperties(depense,depenseDTO);
        return depenseDTO;
    }
    public Depense fromDepenseDTO(DepenseDTO depenseDTO){
        Depense depense=new Depense();
        BeanUtils.copyProperties(depenseDTO,depense);
        return depense;
    }
}
