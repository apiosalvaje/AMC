package Algoritmos;

import java.util.*;
import Datos.*;

public class DivideYVencerasOpt extends Algoritmo {

    public DivideYVencerasOpt(List<Punto> dataset) {
        this.dataset = dataset;
    }

    @Override
    public void run() {
        List<Punto> puntosOrdenadosX = new ArrayList<>(dataset);
        List<Punto> puntosOrdenadosY = new ArrayList<>(dataset);

        // Ordenar por X e Y
        Quicksort.Ordena(puntosOrdenadosX);
        Quicksort.OrdenaY(puntosOrdenadosY);

        List<Punto> resultado = buscarParMasCercanoOpt(puntosOrdenadosX, puntosOrdenadosY);
        if (resultado != null &&  resultado.size() == 2){
        }
    }

    private List<Punto> buscarParMasCercanoOpt(List<Punto> puntosOrdenadosX, List<Punto> puntosOrdenadosY) {
        int n = puntosOrdenadosX.size();
        if (n <= 10) {
            // Caso base: usar búsqueda Con Poda
            return buscarParConPoda(puntosOrdenadosX);
        }

        int mitad = n / 2;
        Punto puntoCorte = puntosOrdenadosX.get(mitad);

        List<Punto> izquierdaX = puntosOrdenadosX.subList(0, mitad);
        List<Punto> derechaX = puntosOrdenadosX.subList(mitad, n);

        List<Punto> izquierdaY = new ArrayList<>();
        List<Punto> derechaY = new ArrayList<>();

        // Dividir ptsY en izquierdaY y derechaY basados en X
        for (Punto p : puntosOrdenadosY) {
            if (p.getX() <= puntoCorte.getX()) {
                izquierdaY.add(p);
            } else {
                derechaY.add(p);
            }
        }

        List<Punto> resIzquierda = buscarParMasCercanoOpt(izquierdaX, izquierdaY);
        List<Punto> resDerecha = buscarParMasCercanoOpt(derechaX, derechaY);

        double distIzq = calcularDistancia(resIzquierda);
        double distDer = calcularDistancia(resDerecha);

        List<Punto> resultadoMenor = (distIzq < distDer) ? resIzquierda : resDerecha;
        double dmin = Math.min(distIzq, distDer);

        // Construir franja de puntos cerca del punto de corte
        List<Punto> franja = new ArrayList<>();
        for (Punto p : puntosOrdenadosY) {
            if (Math.abs(p.getX() - puntoCorte.getX()) < dmin) {
                franja.add(p);
            }
        }
        buscarParConPoda(franja);
        return resultadoMenor;
    }
    private double calcularDistancia(List<Punto> par) {
        if (par == null || par.size() < 2) return Double.MAX_VALUE;
        return DE.calcula(par.get(0), par.get(1));
    }

    private List<Punto> buscarParConPoda(List<Punto> puntos) {
    
    double distanciaMin = Double.POSITIVE_INFINITY;
    List<Punto> parMejorLocal = new ArrayList<>();

    for (int i = 0; i < puntos.size(); i++) {
        Punto p1 = puntos.get(i);

        for (int j = i + 1; j < puntos.size() && j <= i + 12; j++) {
            Punto p2 = puntos.get(j);

            double dx = Math.abs(p1.getX() - p2.getX());
            if (dx >= distanciaMin) {
                break; // poda temprana si hace falta
            }

            double d = this.DE.calcula(p1, p2);
            if (d < distanciaMin) {
                distanciaMin = d;
                parMejorLocal.clear();
                parMejorLocal.add(p1);
                parMejorLocal.add(p2);
            }
        }
    }

    if (distanciaMin < this.MejorDis) {
        this.ParMejor.clear();
        this.ParMejor.addAll(parMejorLocal);
        this.MejorDis = distanciaMin;
    }
    return this.ParMejor;
}


}
