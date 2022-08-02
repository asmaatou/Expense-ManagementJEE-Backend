package com.stage.gestionnoteback;

import com.stage.gestionnoteback.entities.Employe;
import com.stage.gestionnoteback.repositories.DepenseRepository;
import com.stage.gestionnoteback.repositories.EmployeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.Stream;

@SpringBootApplication
public class GestionNoteBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionNoteBackApplication.class, args);
	}

	CommandLineRunner start(EmployeRepository employeRepository, DepenseRepository depenseRepository){
		return args -> {
			Stream.of("Amine","Yassine","Hiba").forEach(name->{
				Employe employe = new Employe();
				employe.setFname(name);
				employe.setEmail(name+"@gmail.com");
			});

		};
	}

}
