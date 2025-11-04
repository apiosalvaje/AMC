package Algoritmos;

import java.util.*;
import Datos.*;

public abstract class Algoritmo { //Clase base para otras clases que heredan

    public DistanciaEuc DE = new DistanciaEuc();
    public List<Punto> ParMejor = new ArrayList<>();
    public List<Punto> dataset;
    public double MejorDis = Double.POSITIVE_INFINITY;

    public int distanciacalculada (){
        return DE.calculo;
    }

    public double mejor_distancia(){
        return MejorDis;
    }

    public abstract void run();

    public List<Punto> parmejor(){
        return ParMejor;
    }
}
