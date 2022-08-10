package com.stage.gestionnoteback;

import com.stage.gestionnoteback.dtos.AdminDTO;
import com.stage.gestionnoteback.dtos.EmployeDTO;
import com.stage.gestionnoteback.dtos.ManagerDTO;
import com.stage.gestionnoteback.entities.Employe;
import com.stage.gestionnoteback.enums.DepenseType;
import com.stage.gestionnoteback.enums.InventionType;
import com.stage.gestionnoteback.exceptions.EmployeNotFoundException;
import com.stage.gestionnoteback.services.GestionNoteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class GestionNoteBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionNoteBackApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(GestionNoteService gestionNoteService){
		return args -> {
			Stream.of("Anass","Nada","Ayoub").forEach(name->{
				EmployeDTO employe= new EmployeDTO();
				employe.setUserName(name+UUID.randomUUID());
				employe.setFullName(name);
				employe.setEmail(name+"@gmail.com");
				gestionNoteService.saveEmploye(employe);
			});
			Stream.of("Amine","Kawtar","Wafaa").forEach(name->{
				ManagerDTO manager= new ManagerDTO();
				manager.setUserName(name+UUID.randomUUID());
				manager.setFullName(name);
				manager.setEmail(name+"@gmail.com");
				gestionNoteService.saveManager(manager);
			});
			Stream.of("Yassine","Hajar","Reda").forEach(name->{
				AdminDTO admin= new AdminDTO();
				admin.setUserName(name+UUID.randomUUID());
				admin.setFullName(name);
				admin.setEmail(name+"@gmail.com");
				gestionNoteService.saveAdmin(admin);
			});
			gestionNoteService.listEmployes().forEach(personne -> {
				try {
					gestionNoteService.saveDepense("Dehbi","oracle", InventionType.Installation, DepenseType.TAXI,35.5,"", personne.getId());
					gestionNoteService.saveDepense("Boussmah","flutter", InventionType.Installation, DepenseType.REPAS,55.5,"", personne.getId());
				} catch (EmployeNotFoundException e) {
					e.printStackTrace();
				}
			});
		};
	}
}
