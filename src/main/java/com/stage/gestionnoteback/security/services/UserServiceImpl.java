package com.stage.gestionnoteback.security.services;

import com.stage.gestionnoteback.dtos.UserDTO;
import com.stage.gestionnoteback.mappers.GestionNoteMapperImpl;
import com.stage.gestionnoteback.models.User;
import com.stage.gestionnoteback.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    private GestionNoteMapperImpl dtoMapper;
    private UserRepository userRepository;
    @Override
    public List<UserDTO> searchUser(String keyword){
        List<User> users=userRepository.searchUser(keyword);
        List<UserDTO> userDTOS=users.stream().map(user -> dtoMapper.fromUser(user)).collect(Collectors.toList());
        return  userDTOS;
    }
    @Override
    public UserDTO updateUser(UserDTO userDTO){
        User user=dtoMapper.fromUserDTO(userDTO);
        User savedUser = userRepository.save(user);
        return dtoMapper.fromUser(savedUser);
    }
    @Override
    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }


}
