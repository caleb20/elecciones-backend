package com.caleb.elecciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.caleb.elecciones.auth", "com.caleb.elecciones"})
public class EleccionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EleccionesApplication.class, args);
	}

}
