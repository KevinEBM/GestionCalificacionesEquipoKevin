package com.home.service.impl;

import com.home.model.Estudiante;
import com.home.repository.EstudianteRepository;
import com.home.service.CalificacionService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CalificacionServiceImpl implements CalificacionService {

    private static final double NOTA_APROBACION = 3.0;

    private final EstudianteRepository repository;

    public CalificacionServiceImpl(EstudianteRepository repository) {
        this.repository = repository;
    }

    @Override
    public void registrarEstudiante(String nombre, List<Double> notas) {
        repository.guardar(new Estudiante(nombre, notas));
    }

    @Override
    public List<Estudiante> listarEstudiantes() {
        List<Estudiante> lista = repository.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
        } else {
            System.out.println("\n--- Listado de Estudiantes ---");
            lista.forEach(System.out::println);
        }
        return lista;
    }

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

    @Override
    public double calcularPromedioGeneral() {
        return repository.listarTodos().stream()
                .mapToDouble(Estudiante::calcularPromedioIndividual)
                .average()
                .orElse(0.0);
    }

    @Override
    // función: valentina
    public Estudiante obtenerEstudianteConMayorNota() {
        return repository.listarTodos().stream()
                .max(Comparator.comparingDouble(Estudiante::calcularPromedioIndividual))
                .orElse(null);
    }
}
