package com.home.repository;

import com.home.model.Estudiante;

import java.util.ArrayList;
import java.util.List;

public class EstudianteRepositoryImpl implements EstudianteRepository {

    private final List<Estudiante> estudiantes;

    public EstudianteRepositoryImpl() {
        estudiantes = new ArrayList<>();
    }

    @Override
    public void guardar(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    @Override
    public List<Estudiante> listarTodos() {
        return new ArrayList<>(estudiantes);
    }

    @Override
    public Estudiante buscarPorNombre(String nombre) {
        return estudiantes.stream()
                .filter(e -> e.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }
}
