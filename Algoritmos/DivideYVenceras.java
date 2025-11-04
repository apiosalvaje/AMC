package Algoritmos;

import java.util.*;
import Datos.*;

public class DivideYVenceras extends Algoritmo {

    public DivideYVenceras(List<Punto> dataset) {
        this.dataset = dataset;
    }

    @Override
    public void run() {
        List<Punto> puntosOrdenadosX = new ArrayList<>(dataset);
        List<Punto> puntosOrdenadosY = new ArrayList<>(dataset);

        // Ordenar por X e Y
        Quicksort.Ordena(puntosOrdenadosX);
        Quicksort.OrdenaY(puntosOrdenadosY);

        List<Punto> resultado = buscarParMasCercanoDivideYVenceras(puntosOrdenadosX, puntosOrdenadosY);
        if (resultado != null &&  resultado.size() == 2){
        // ParMejor.clear();
        // ParMejor.addAll(resultado);
        }
    }

    private List<Punto> buscarParMasCercanoDivideYVenceras(List<Punto> puntosOrdenadosX, List<Punto> puntosOrdenadosY) {
        int n = puntosOrdenadosX.size();
        if (n <= 10) {
            // Caso base: usar búsqueda exhaustiva
            return buscarParExhaustivo(puntosOrdenadosX);
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

        List<Punto> resIzquierda = buscarParMasCercanoDivideYVenceras(izquierdaX, izquierdaY);
        List<Punto> resDerecha = buscarParMasCercanoDivideYVenceras(derechaX, derechaY);

        double distIzq = calcularDistancia(resIzquierda);
        double distDer = calcularDistancia(resDerecha);

        List<Punto> resultadoMenor = (distIzq < distDer) ? resIzquierda : resDerecha;
        double dmin = Math.min(distIzq, distDer);

        // Construir franja de puntos cerca del punto de corte
        List<Punto> franja = new ArrayList<>();
        for (Punto p : puntosOrdenadosX) {
            if (Math.abs(p.getX() - puntoCorte.getX()) < dmin) {
                franja.add(p);
            }
        }
        buscarParExhaustivo(franja);
        return resultadoMenor;
    }
    private double calcularDistancia(List<Punto> par) {
        if (par == null || par.size() < 2) return Double.MAX_VALUE;
        return DE.calcula(par.get(0), par.get(1));
    }

    private List<Punto> buscarParExhaustivo(List<Punto> puntos) {
        Exhaustivo exhaustivo = new Exhaustivo(puntos);
        exhaustivo.run();  // Ejecuta el algoritmo exhaustivo en este subconjunto
    
        // También puedes guardar la distancia si quieres usarla después
        if (exhaustivo.mejor_distancia() < this.MejorDis) {
            this.ParMejor.clear();
            this.ParMejor.addAll(exhaustivo.ParMejor);
            this.MejorDis = exhaustivo.MejorDis;
        }
        this.DE.calculo += exhaustivo.distanciacalculada();

        return exhaustivo.ParMejor;
    }

    public double ejecutarConMedicion() {
        long inicio = System.nanoTime();
        run();
        long fin = System.nanoTime();
        return (fin - inicio) / 1_000_000.0; // en milisegundos con decimales
    }

}
