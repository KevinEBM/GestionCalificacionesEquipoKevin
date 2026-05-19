package com.home.main;

import com.home.controller.MenuController;
import com.home.repository.EstudianteRepository;
import com.home.repository.EstudianteRepositoryImpl;
import com.home.service.CalificacionService;
import com.home.service.impl.CalificacionServiceImpl;
import com.home.validators.EntradaValidator;

import java.util.Scanner;

/**
 * GestionCalificaciones - Responsabilidad ÚNICA: Inicializar la aplicación
 * SOLID - Composition Root Pattern
 * 
 * Solo:
 * 1. Crea las dependencias
 * 2. Las inyecta en MenuController
 * 3. Inicia el flujo principal
 */
public class GestionCalificaciones {
    public static void main(String[] args) {
        System.out.println("Bienvenido al Sistema de Gestión de Calificaciones\n");

        // Composición de dependencias (Composition Root)
        Scanner scanner = new Scanner(System.in);
        EstudianteRepository repository = new EstudianteRepositoryImpl();
        CalificacionService calificacionService = new CalificacionServiceImpl(repository);
        EntradaValidator validator = new EntradaValidator(scanner);

        // Inyección y lanzamiento del controlador
        MenuController menuController = new MenuController(calificacionService, validator, scanner);
        menuController.iniciarAplicacion();

        // Limpieza
        scanner.close();
    }
}

