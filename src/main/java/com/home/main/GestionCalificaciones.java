package com.home.main;

import com.home.model.Estudiante;
import com.home.repository.EstudianteRepository;
import com.home.repository.EstudianteRepositoryImpl;
import com.home.service.CalificacionService;
import com.home.service.impl.CalificacionServiceImpl;
import com.home.validators.EntradaValidator;

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
            System.out.println("4. Mostrar mayor nota");
            System.out.println("5. Mostrar aprobados");
            System.out.println("6. Buscar estudiante");
            System.out.println("0. Salir");

            System.out.print("Seleccione opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    registrarEstudiante(service, validator);
                    break;
                case 2:
                    service.listarEstudiantes();
                    break;
                case 3:
                    mostrarPromedioGeneral(service);
                    break;
                case 4:
                    mostrarMayorNota(service);
                    break;
                case 5:
                    mostrarAprobados(service);
                    break;
                case 6:
                    buscarEstudiante(service, sc);
                    break;
                case 0:
                    System.out.println("Saliendo... ");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 0);

        sc.close();
    }

    private static void registrarEstudiante(CalificacionService service, EntradaValidator validator) {
        try {
            String nombre = validator.leerNombre();
            int cantidad = validator.leerCantidadNotas();
            List<Double> notas = validator.leerNotas(cantidad);
            service.registrarEstudiante(nombre, notas);
            System.out.println("Estudiante registrado correctamente.");
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    private static void mostrarPromedioGeneral(CalificacionService service) {
        System.out.printf("Promedio general del grupo: %.2f%n", service.calcularPromedioGeneral());
    }

    // función: valentina
    private static void mostrarMayorNota(CalificacionService service) {
        Estudiante mejor = service.obtenerEstudianteConMayorNota();
        if (mejor == null) {
            System.out.println("No hay estudiantes registrados.");
        } else {
            System.out.println("\n--- Estudiante con mayor nota ---");
            System.out.println(mejor);
        }
    }

    // función: valentina
    private static void mostrarAprobados(CalificacionService service) {
        List<Estudiante> aprobados = service.obtenerEstudiantesAprobados();
        if (aprobados.isEmpty()) {
            System.out.println("No hay estudiantes aprobados.");
            return;
        }
        System.out.println("\n--- Estudiantes aprobados ---");
        aprobados.forEach(System.out::println);
    }

    private static void buscarEstudiante(CalificacionService service, Scanner sc) {
        System.out.print("Ingrese nombre a buscar: ");
        String nombreBuscar = sc.nextLine();
        service.buscarEstudiantePorNombre(nombreBuscar);
    }
}
