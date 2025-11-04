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
        if (n <= 2) {
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
        ConPoda conpoda = new ConPoda(puntos);
        conpoda.run();  // Ejecuta el algoritmo exhaustivo en este subconjunto
    
        // También puedes guardar la distancia si quieres usarla después
        if (conpoda.mejor_distancia() < this.MejorDis) {
            this.ParMejor.clear();
            this.ParMejor.addAll(conpoda.ParMejor);
            this.MejorDis = conpoda.MejorDis;
        }
        this.DE.calculo += conpoda.distanciacalculada();

        return conpoda.ParMejor;
    }

    public double ejecutarConMedicion() {
        long inicio = System.nanoTime();
        run();
        long fin = System.nanoTime();
        return (fin - inicio) / 1_000_000.0; // en milisegundos con decimales
    }

}
