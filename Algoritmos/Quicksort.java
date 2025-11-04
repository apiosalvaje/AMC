package Algoritmos;

import java.util.*;

import Datos.*;

public final class Quicksort { //Prohibimos la herencia de esta clase con "final"
    
    // Ordenar por X
    public static void Ordena(List<Punto> lista) {
        quicksort(lista, 0, lista.size() - 1);
    }

    private static void quicksort(List<Punto> lista, int primero, int ultimo) {
        if (primero >= ultimo) return;
        Punto pivote = lista.get(ultimo);
        int pos = primero;

        for (int i = primero; i < ultimo; i++) {
            if (lista.get(i).getX() <= pivote.getX()) {
                Collections.swap(lista, i, pos);
                pos++;
            }
        }

        Collections.swap(lista, pos, ultimo);

        quicksort(lista, primero, pos - 1);
        quicksort(lista, pos + 1, ultimo);
    }

    // Ordenar por Y
    public static void OrdenaY(List<Punto> lista) {
        quicksortY(lista, 0, lista.size() - 1);
    }

    private static void quicksortY(List<Punto> lista, int primero, int ultimo) {
        if (primero >= ultimo) return;
        Punto pivote = lista.get(ultimo);
        int pos = primero;

        for (int i = primero; i < ultimo; i++) {
            if (lista.get(i).getY() <= pivote.getY()) {
                Collections.swap(lista, i, pos);
                pos++;
            }
        }

        Collections.swap(lista, pos, ultimo);

        quicksortY(lista, primero, pos - 1);
        quicksortY(lista, pos + 1, ultimo);
    }
}