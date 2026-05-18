package com.home.model;

import java.util.ArrayList;
import java.util.List;

public class Estudiante {
    private String nombre;
    private List<Double> notas;

    public Estudiante(String nombre) {
        this.nombre = nombre;
        this.notas = new ArrayList<>();
    }

    public void agregarNota(double nota) {
        notas.add(nota);
    }

    // Tu parte: calcular promedio individual
    public double calcularPromedioIndividual() {
        return notas.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public String getNombre() { return nombre; }
    public List<Double> getNotas() { return notas; }
}
