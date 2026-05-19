package com.home.service.impl;

import com.home.model.Estudiante;
import com.home.repository.EstudianteRepository;
import com.home.service.CalificacionService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

 /**
 * CalificacionServiceImpl - Lógica de negocio limpia
 * SOLID - Single Responsibility Principle (solo cálculos y gestión de datos)
 */
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
        return repository.listarTodos();
    }

    @Override
    public Estudiante buscarEstudiantePorNombre(String nombre) {
        return repository.buscarPorNombre(nombre);
    }

    @Override
    public double calcularPromedioGeneral() {
        return repository.listarTodos().stream()
                .mapToDouble(Estudiante::calcularPromedioIndividual)
                .average()
                .orElse(0.0);
    }

    @Override
    public Estudiante obtenerEstudianteConMayorNota() {
        return repository.listarTodos().stream()
                .max(Comparator.comparingDouble(Estudiante::calcularPromedioIndividual))
                .orElse(null);
    }

    @Override
    public List<Estudiante> obtenerEstudiantesAprobados() {
        return repository.listarTodos().stream()
                .filter(e -> e.calcularPromedioIndividual() >= NOTA_APROBACION)
                .collect(Collectors.toList());
    }
}
