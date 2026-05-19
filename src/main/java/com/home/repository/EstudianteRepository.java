package com.home.repository;

import com.home.model.Estudiante;

import java.util.List;

public interface EstudianteRepository {

    void guardar(Estudiante estudiante);

    List<Estudiante> listarTodos();

    Estudiante buscarPorNombre(String nombre);

}
