package com.historykeepers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Clase principal del backend, aquí es donde se arranca toda la app de Spring Boot.
@SpringBootApplication
public class HistoryKeepersApplication {

	public static void main(String[] args) {
		// Aquí se levanta el servidor y se cargan todos los controllers, services, etc.
		SpringApplication.run(HistoryKeepersApplication.class, args);
	}

}
