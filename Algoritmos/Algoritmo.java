package Algoritmos;

import java.util.*;
import Datos.*;

public abstract class Algoritmo { //Clase base para otras clases que heredan

    public DistanciaEuc DE = new DistanciaEuc(); //Esta es la instancia para calcular las distancias
    public List<Punto> ParMejor = new ArrayList<>(); //Este será el mejor par encontrado
    public List<Punto> dataset; //Aqui tendremos el dataset actual
    public double MejorDis = Double.POSITIVE_INFINITY; //Esta será la mejor distancia que la inicializamos en +infinito

    public int distanciacalculada (){
        return DE.calculo; //Devolvemos el número de cálculos de distancias que se han realizado
    }

    public double mejor_distancia(){
        return MejorDis; //Devolvemos la mejor distancia mínima encontrada
    }

    public abstract void run(); //Este es un método abstracto que ejecuta el algoritmo

    public List<Punto> parmejor(){
        return ParMejor; //Devolvemos el mejor par de puntos encontrado
    }

    public double ejecutarConMedicion() {
        long inicio = System.nanoTime();
        this.run(); //Ejecuta el algoritmo directamente
        long fin = System.nanoTime();
        return (fin - inicio) / 1_000_000.0; //Tiempo en milisegundos s
    }
}
