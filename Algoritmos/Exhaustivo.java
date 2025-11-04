package Algoritmos;
import java.util.*;
import Datos.*;

public class Exhaustivo extends Algoritmo{

    public Exhaustivo(List<Punto> dataset){
        this.dataset = dataset;
    }

    @Override
    public void run() {
        double distanciaMin = mejor_distancia();

        for (int i = 0; i < dataset.size() - 1; i++){
            for (int j = i + 1; j < dataset.size(); j++){
                double d = this.DE.calcula(this.dataset.get(i), this.dataset.get(j));

                if (d < distanciaMin) {
                    distanciaMin = d;
                    ParMejor.clear();
                    ParMejor.add(this.dataset.get(i));
                    ParMejor.add(this.dataset.get(j));
                }
            } 
        }
        this.MejorDis = distanciaMin;
    }

    public double ejecutarConMedicion() {
        long inicio = System.nanoTime();
        run();
        long fin = System.nanoTime();
        return (fin - inicio) / 1_000_000.0; // en milisegundos con decimales
    }


}