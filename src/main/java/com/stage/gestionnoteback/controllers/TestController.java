package com.stage.gestionnoteback.controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



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
  }@GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return "Admin Board.";
  }

}
