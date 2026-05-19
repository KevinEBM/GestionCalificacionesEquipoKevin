package com.home.main;

import com.home.repository.EstudianteRepository;
import com.home.repository.EstudianteRepositoryImpl;
import com.home.service.CalificacionService;
import com.home.service.impl.CalificacionServiceImpl;
import com.home.validators.EntradaValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionCalificaciones {
    public static void main(String[] args) {
        System.out.println("Bienvenido al Sistema de Gestión de Calificaciones");

        Scanner sc = new Scanner(System.in);
        EntradaValidator validator = new EntradaValidator(sc);

        EstudianteRepository repository = new EstudianteRepositoryImpl();

        CalificacionService service = new CalificacionServiceImpl(repository);

        int opcion;

        do {

            System.out.println("\n===== SISTEMA DE CALIFICACIONES =====");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Mostrar estudiantes");
            System.out.println("3. Calcular promedio general");
            System.out.println("4. Mostrar mejor estudiante");
            System.out.println("5. Mostrar aprobados");
            System.out.println("6. Buscar estudiante");
            System.out.println("0. Salir");

            System.out.print("Seleccione opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:

                    try {
                        String nombre   = validator.leerNombre();
                        int cantidad    = validator.leerCantidadNotas();
                        List<Double> notas = validator.leerNotas(cantidad);

                        service.registrarEstudiante(nombre, notas);
                        System.out.println("Estudiante registrado correctamente.");

                    } catch (Exception e) {
                        System.out.println("Error inesperado: " + e.getMessage());
                    }
                    break;

                case 2:
                    service.listarEstudiantes();
                    break;

                case 6:
                    System.out.print("Ingrese nombre a buscar: ");
                    String nombreBuscar = sc.nextLine();
                    service.buscarEstudiantePorNombre(nombreBuscar);
                    break;

                case 0:

                    System.out.println("Saliendo... ");
                    break;

                default:
                    System.out.println("Opción inválida");
            }
        }while (opcion != 0);

        sc.close();
    }
}