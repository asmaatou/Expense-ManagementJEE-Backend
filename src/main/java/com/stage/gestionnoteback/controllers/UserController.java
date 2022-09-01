package com.stage.gestionnoteback.controllers;

import com.stage.gestionnoteback.dtos.UserDTO;
import com.stage.gestionnoteback.models.User;
import com.stage.gestionnoteback.repository.UserRepository;
import com.stage.gestionnoteback.security.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/usr")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @GetMapping("/getEmploye")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getEmploye(){
        int role = 1 ;
        List<User> employe = userRepository.findUserByRole(role);
        return employe ;
    }
    @GetMapping("/getManager")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getManager(){
        int role = 2 ;
        List<User> manager = userRepository.findUserByRole(role);
        return manager ;
    }
    @GetMapping("/getAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAdmin(){
        int role = 3 ;
        List<User> admin = userRepository.findUserByRole(role);
        return admin ;
    }
    @GetMapping("/user/search")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDTO> searchUsers(@RequestParam(name = "keyword",defaultValue = "")String keyword){
        return userService.searchUser("%"+keyword+"%");
    }
    @PutMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDTO updateUser(@PathVariable Long userId,@RequestBody UserDTO userDTO){
        userDTO.setId(userId);
        return userService.updateUser(userDTO);
    }
    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }






}
