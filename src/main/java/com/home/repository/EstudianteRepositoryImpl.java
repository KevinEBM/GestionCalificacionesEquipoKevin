package com.home.repository;

import com.home.model.Estudiante;

import java.util.ArrayList;
import java.util.List;

public class EstudianteRepositoryImpl  implements EstudianteRepository {

    private final List<Estudiante> estudiantes;

    public EstudianteRepositoryImpl() {
        estudiantes = new ArrayList<>();
    }

    @Override
    public void guardar(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }
}
