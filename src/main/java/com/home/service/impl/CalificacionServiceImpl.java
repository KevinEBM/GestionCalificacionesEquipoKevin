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

        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("Nombre inválido");
        }

        if (notas.isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar al menos una nota");
        }

        for (double nota : notas) {

            if (nota < 0 || nota > 5) {
                throw new IllegalArgumentException("Las notas deben estar entre 0 y 5");
            }
        }

        Estudiante estudiante = new Estudiante(nombre, notas);

        repository.guardar(estudiante);
    }

}
