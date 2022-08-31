package com.stage.gestionnoteback.security.services;

import com.stage.gestionnoteback.dtos.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> searchUser(String keyword);

    UserDTO updateUser(UserDTO userDTO);

    void deleteUser(Long userId);
}
