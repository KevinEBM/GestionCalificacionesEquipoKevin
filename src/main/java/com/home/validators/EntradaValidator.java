package com.home.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EntradaValidator {

    private static final double NOTA_MIN = 0.0;
    private static final double NOTA_MAX = 5.0;

    private final Scanner sc;

    public EntradaValidator(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Solicita y valida el nombre del estudiante.
     * Repite hasta recibir un valor no vacío.
     */
    public String leerNombre() {
        while (true) {
            System.out.print("Nombre del estudiante: ");
            String nombre = sc.nextLine().trim();

            if (nombre.isEmpty()) {
                System.out.println("El nombre no puede estar vacío. Intente de nuevo.");
                continue;
            }

            if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                System.out.println("El nombre solo puede contener letras y espacios.");
                continue;
            }

            return nombre;
        }
    }

    /**
     * Solicita y valida la cantidad de notas.
     * Debe ser un entero positivo.
     */
    public int leerCantidadNotas() {
        while (true) {
            System.out.print("Cantidad de notas: ");
            try {
                int cantidad = Integer.parseInt(sc.nextLine().trim());

                if (cantidad <= 0) {
                    System.out.println("Debe ingresar al menos una nota.");
                    continue;
                }

                return cantidad;

            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    /**
     * Solicita y valida una nota individual.
     * Acepta coma o punto como separador decimal.
     * Rango permitido: 0.0 – 5.0
     */
    public double leerNota(int numero) {
        while (true) {
            System.out.print("Ingrese nota " + numero + " (ej: 4,5 o 4.5): ");
            try {
                String entrada = sc.nextLine().trim().replace(",", ".");
                double nota = Double.parseDouble(entrada);

                if (nota < NOTA_MIN || nota > NOTA_MAX) {
                    System.out.printf("La nota debe estar entre %.1f y %.1f.%n", NOTA_MIN, NOTA_MAX);
                    continue;
                }

                return nota;

            } catch (NumberFormatException e) {
                System.out.println("Formato inválido. Ejemplo válido: 4,5");
            }
        }
    }
    /**
     * Construye la lista de notas llamando a leerNota() por cada posición.
     */
    public List<Double> leerNotas(int cantidad) {
        List<Double> notas = new ArrayList<>();
        for (int i = 1; i <= cantidad; i++) {
            notas.add(leerNota(i));
        }
        return notas;
    }
}

