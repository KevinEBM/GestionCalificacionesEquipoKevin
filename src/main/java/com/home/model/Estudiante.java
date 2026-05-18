package com.home.model;

import java.util.List;

public class Estudiante {

    private final String nombre;
    private final List<Double> notas;

    public Estudiante(String nombre, List<Double> notas) {
        this.nombre = nombre;
        this.notas = notas;
    }

    public String getNombre() {return nombre;}

    public List<Double> getNotas() {return notas;}


    @Override
    public String toString() {

        StringBuilder notasTexto = new StringBuilder();

        for (int i = 0; i < notas.size(); i++) {

            notasTexto.append(notas.get(i));

            if (i < notas.size() - 1) {
                notasTexto.append(", ");
            }
        }

        return """
            ==============================
            Estudiante: %s
            Notas: [%s]
            ==============================
            """.formatted(nombre, notasTexto);
    }
}
