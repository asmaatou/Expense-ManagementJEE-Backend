package com.stage.gestionnoteback.controllers;

import com.stage.gestionnoteback.models.User;
import com.stage.gestionnoteback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/test")
public class TestController {



  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/employe")
  @PreAuthorize("hasRole('EMPLOYE')")
  public String employeAccess() {
    return "EMPLOYE Content.";
  }

  @GetMapping("/manager")
  @PreAuthorize("hasRole('MANAGER')")
  public String managerAccess() { return "MANAGER Board.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return "Admin Board.";
  }


}
