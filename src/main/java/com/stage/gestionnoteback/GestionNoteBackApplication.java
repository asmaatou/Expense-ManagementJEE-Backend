package com.stage.gestionnoteback;

import com.stage.gestionnoteback.dtos.EmployeDTO;
import com.stage.gestionnoteback.entities.Employe;
import com.stage.gestionnoteback.entities.Personne;
import com.stage.gestionnoteback.enums.DepenseType;
import com.stage.gestionnoteback.enums.InventionType;
import com.stage.gestionnoteback.enums.Roles;
import com.stage.gestionnoteback.exceptions.EmployeNotFoundException;
import com.stage.gestionnoteback.repositories.DepenseRepository;
import com.stage.gestionnoteback.repositories.EmployeRepository;
import com.stage.gestionnoteback.repositories.PersonneRepository;
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
			gestionNoteService.listEmployes().forEach(personne -> {
				try {
					gestionNoteService.saveDepense("Dehbi","oracle", InventionType.Installation, DepenseType.TAXI,35.5, personne.getId());
				} catch (EmployeNotFoundException e) {
					e.printStackTrace();
				}
			});
		};
	}
	//@Bean
	CommandLineRunner start(PersonneRepository personneRepository, DepenseRepository depenseRepository){
		return args -> {
			Stream.of("Anass","Nada","Ayoub").forEach(name->{
                Personne personne= new Personne();
				personne.setUserName(name+UUID.randomUUID());
				personne.setFullName(name);
				personne.setEmail(name+"@gmail.com");
				personne.setRole(Roles.valueOf("Admin"));
                personneRepository.save(personne);
			});
		};
	}

}
