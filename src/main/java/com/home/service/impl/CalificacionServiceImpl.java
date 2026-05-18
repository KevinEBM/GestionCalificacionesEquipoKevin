package com.home.service.impl;

import com.home.model.Estudiante;
import java.util.List;

public class CalificacionServiceImpl {

    private List<Estudiante> estudiantes;

    public CalificacionServiceImpl(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
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