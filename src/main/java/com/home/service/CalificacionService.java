package com.home.service;

import com.home.model.Estudiante;

import java.util.List;

public interface CalificacionService {

    void registrarEstudiante(String nombre, List<Double> notas);

    List<Estudiante> listarEstudiantes();

    Estudiante buscarEstudiantePorNombre(String nombre);

    double calcularPromedioGeneral();

    Estudiante obtenerEstudianteConMayorNota();
}
