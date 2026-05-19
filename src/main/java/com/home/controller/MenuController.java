package com.home.controller;

import com.home.model.Estudiante;
import com.home.service.CalificacionService;
import com.home.validators.EntradaValidator;

import java.util.List;
import java.util.Scanner;

/**
 * MenuController - Responsabilidad ÚNICA: Gestionar el flujo del menú
 * SOLID - Single Responsibility Principle
 * 
 * Coordina:
 * - Visualización del menú
 * - Captura de opciones
 * - Delegación de operaciones a servicios
 */
public class MenuController {

    private final CalificacionService calificacionService;
    private final EntradaValidator validator;
    private final Scanner scanner;

    public MenuController(CalificacionService calificacionService, 
                        EntradaValidator validator, 
                        Scanner scanner) {
        this.calificacionService = calificacionService;
        this.validator = validator;
        this.scanner = scanner;
    }

    /**
     * Inicia el flujo principal del menú
     * Responsabilidad: Controlar el loop del programa
     */
    public void iniciarAplicacion() {
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();
            procesarOpcion(opcion);
        } while (opcion != 0);

        System.out.println("Saliendo... ");
    }

    /**
     * Muestra el menú principal
     */
    private void mostrarMenu() {
        System.out.println("\n===== SISTEMA DE CALIFICACIONES =====");
        System.out.println("1. Registrar estudiante");
        System.out.println("2. Mostrar estudiantes");
        System.out.println("3. Calcular promedio general");
        System.out.println("4. Mostrar mayor nota");
        System.out.println("5. Mostrar aprobados");
        System.out.println("6. Buscar estudiante");
        System.out.println("0. Salir");
        System.out.print("Seleccione opción: ");
    }

    /**
     * Procesa la opción seleccionada
     * Responsabilidad: Dispatcher de operaciones
     */
    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> registrarEstudiante();
            case 2 -> listarEstudiantes();
            case 3 -> mostrarPromedioGeneral();
            case 4 -> mostrarMayorNota();
            case 5 -> mostrarAprobados();
            case 6 -> buscarEstudiante();
            case 0 -> {} // Salida controlada en iniciarAplicacion()
            default -> System.out.println("Opción inválida");
        }
    }

    /**
     * Registra un nuevo estudiante
     * Responsabilidad: Coordinar entrada → validación → persistencia
     */
    private void registrarEstudiante() {
        try {
            String nombre = validator.leerNombre();
            int cantidad = validator.leerCantidadNotas();
            List<Double> notas = validator.leerNotas(cantidad);
            calificacionService.registrarEstudiante(nombre, notas);
            System.out.println("✓ Estudiante registrado correctamente.");
        } catch (Exception e) {
            System.out.println("✗ Error inesperado: " + e.getMessage());
        }
    }

    /**
     * Lista todos los estudiantes
     * Responsabilidad: Obtener datos y mostrar
     */
    private void listarEstudiantes() {
        List<Estudiante> estudiantes = calificacionService.listarEstudiantes();
        if (estudiantes.isEmpty()) {
            System.out.println("✗ No hay estudiantes registrados.");
        } else {
            System.out.println("\n--- Listado de Estudiantes ---");
            estudiantes.forEach(System.out::println);
        }
    }

    /**
     * Muestra el promedio general
     * Responsabilidad: Obtener cálculo y mostrar
     */
    private void mostrarPromedioGeneral() {
        double promedio = calificacionService.calcularPromedioGeneral();
        System.out.printf("✓ Promedio general del grupo: %.2f%n", promedio);
    }

    /**
     * Muestra el estudiante con mayor nota
     * Responsabilidad: Obtener dato y mostrar
     */
    private void mostrarMayorNota() {
        Estudiante mejor = calificacionService.obtenerEstudianteConMayorNota();
        if (mejor == null) {
            System.out.println("✗ No hay estudiantes registrados.");
        } else {
            System.out.println("\n--- Estudiante con mayor nota ---");
            System.out.println(mejor);
        }
    }

    /**
     * Muestra estudiantes aprobados (≥ 3.0)
     * Responsabilidad: Obtener lista y mostrar
     */
    private void mostrarAprobados() {
        List<Estudiante> aprobados = calificacionService.obtenerEstudiantesAprobados();
        if (aprobados.isEmpty()) {
            System.out.println("✗ No hay estudiantes aprobados.");
        } else {
            System.out.println("\n--- Estudiantes aprobados ---");
            aprobados.forEach(System.out::println);
        }
    }

    /**
     * Busca un estudiante por nombre
     * Responsabilidad: Obtener entrada y mostrar resultado
     */
    private void buscarEstudiante() {
        System.out.print("Ingrese nombre a buscar: ");
        String nombre = scanner.nextLine();
        Estudiante encontrado = calificacionService.buscarEstudiantePorNombre(nombre);
        
        if (encontrado == null) {
            System.out.println("✗ No se encontró un estudiante con el nombre: " + nombre);
        } else {
            System.out.println("\n✓ Estudiante encontrado:");
            System.out.println(encontrado);
        }
    }
}

