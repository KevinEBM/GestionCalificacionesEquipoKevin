package com.home.service.impl;

import com.home.model.Estudiante;
import com.home.repository.EstudianteRepository;
import com.home.service.CalificacionService;

import java.util.List;

public class CalificacionServiceImpl implements CalificacionService {

    private final EstudianteRepository repository;

    public CalificacionServiceImpl(EstudianteRepository repository) {
        this.repository = repository;
    }

    @Override
    public void registrarEstudiante(String nombre, List<Double> notas) {
        // Implementado por Aprendiz 1
    }

    // --- Listar todos los estudiantes (Santiago - Aprendiz 2) ---
    @Override
    public List<Estudiante> listarEstudiantes() {
        List<Estudiante> lista = repository.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
        } else {
            System.out.println("\n--- Listado de Estudiantes ---");
            for (Estudiante e : lista) {
                System.out.println(e);
            }
        }
        return lista;
    }

    // --- Buscar estudiante por nombre (Santiago - Aprendiz 2) ---
    @Override
    public Estudiante buscarEstudiantePorNombre(String nombre) {
        Estudiante encontrado = repository.buscarPorNombre(nombre);
        if (encontrado != null) {
            System.out.println("\nEstudiante encontrado:");
            System.out.println(encontrado);
        } else {
            System.out.println("No se encontró un estudiante con el nombre: " + nombre);
        }
        return encontrado;
    }

    // --- Mostrar promedio individual ---
    public void mostrarPromediosIndividuales() {
        System.out.println("\n--- Promedios individuales ---");
        for (Estudiante e : estudiantes) {
            System.out.println(e.getNombre() + ": " + e.calcularPromedioIndividual());
        }
    }

    // --- Calcular y mostrar promedio general ---
    public double calcularPromedioGeneral() {
        return estudiantes.stream()
                .mapToDouble(Estudiante::calcularPromedioIndividual)
                .average()
                .orElse(0.0);
    }

    public void mostrarPromedioGeneral() {
        System.out.println("\nPromedio general del grupo: " + calcularPromedioGeneral());
    }
}