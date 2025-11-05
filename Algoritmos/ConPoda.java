package Algoritmos;

import java.util.*;
import Datos.*;

public class ConPoda extends Algoritmo{

     public ConPoda(List<Punto> dataset){
        this.dataset = dataset; //Este será el dataset a "evaluar"
    }

    @Override
    public void run() {
        double distanciaMin = mejor_distancia();

        for (int i = 0; i < dataset.size() - 1; i++){
            for (int j = i + 1; j < dataset.size(); j++){
                //Sólo calculamos la distancia si la diferencia en x es menor que la actual distancia mínima
                if (Math.abs(this.dataset.get(i).getX() - this.dataset.get(j).getX()) < distanciaMin) {
                    double d = this.DE.calcula(this.dataset.get(i), this.dataset.get(j));
                    if (d < distanciaMin) {
                        distanciaMin = d; //Actualizamos la mejor distancia mínima
                        ParMejor.clear(); //Limpiamos la lista del par mejor
                        ParMejor.add(this.dataset.get(i)); //Añadimos el par mejor actual
                        ParMejor.add(this.dataset.get(j)); //Añadimos el par mejor actual
                }
                }
            } 
        }
        this.MejorDis = distanciaMin; //Guardamos la distancia mínima encontrada
    }
}
