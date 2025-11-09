package Algoritmos;

import java.util.*;
import Datos.*;

public class DivideYVenceras extends Algoritmo {

    public DivideYVenceras(List<Punto> dataset) {
        this.dataset = dataset; //Este será el dataset a "evaluar"
    }

    @Override
    public void run() {
        List<Punto> puntosOrdenadosX = new ArrayList<>(dataset);

        //Ordenamos por s e y
        Quicksort.Ordena(puntosOrdenadosX);

        List<Punto> resultado = buscarParMasCercanoDivideYVenceras(puntosOrdenadosX); //Ejecutamos el algoritmo recursivo
        if (resultado != null &&  resultado.size() == 2){
        
        }
    }

    private List<Punto> buscarParMasCercanoDivideYVenceras(List<Punto> puntosOrdenadosX) {
        int n = puntosOrdenadosX.size();
        if (n <= 20) {
            //Caso base: usamos búsqueda exhaustiva
            return buscarParConPoda(puntosOrdenadosX);
        }

        int mitad = n / 2;
        Punto puntoCorte = puntosOrdenadosX.get(mitad);

        List<Punto> izquierdaX = puntosOrdenadosX.subList(0, mitad);
        List<Punto> derechaX = puntosOrdenadosX.subList(mitad, n);

        List<Punto> resIzquierda = buscarParMasCercanoDivideYVenceras(izquierdaX);
        List<Punto> resDerecha = buscarParMasCercanoDivideYVenceras(derechaX);

        double distIzq = calcularDistancia(resIzquierda);
        double distDer = calcularDistancia(resDerecha);

        List<Punto> resultadoMenor = (distIzq < distDer) ? resIzquierda : resDerecha;
        double dmin = Math.min(distIzq, distDer);

        //Construimos franja de puntos cerca del punto de corte
        List<Punto> franja = new ArrayList<>();
        for (Punto p : puntosOrdenadosX) {
            if (Math.abs(p.getX() - puntoCorte.getX()) < dmin) {
                franja.add(p);
            }
        }
        buscarParConPoda(franja); //Buscamos en la franja
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

            for (int j = i + 1; j < puntos.size(); j++) {
                double dx = Math.abs(p1.getX() - puntos.get(j).getX());
                if (dx >= distanciaMin) {
                    break; // Poda temprana
                }

                double d = DE.calcula(p1, puntos.get(j));
                if (d < distanciaMin) {
                    distanciaMin = d;
                    parMejorLocal.clear();
                    parMejorLocal.add(p1);
                    parMejorLocal.add(puntos.get(j));
                }
            }
        }

        if (distanciaMin < this.MejorDis) {
            this.ParMejor.clear();
            this.ParMejor.addAll(parMejorLocal);
            this.MejorDis = distanciaMin;
        }

        return parMejorLocal;
    }

}
