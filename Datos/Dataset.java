package Datos;
import java.util.*;

public class Dataset {
    public int cantidad = 100;

    public List<Punto> generarPuntosAleatorios(){ //Este método:
        List<Punto> lista = new ArrayList<>(); //Crea una lista vacía de puntos
        Random rnd = new Random();
        for (int i = 0; i < cantidad; i++) { //Utilizamos este bucle para crear la cantidad de puntos solicitada
            double x = rnd.nextDouble() * 100; //Para cada punto, generamos dos números aleatorios (x,y), ambos entre 0 y 100
            double y = rnd.nextDouble() * 100;
            lista.add(new Punto(x, y,i)); //Creamos el objeto 'Punto' con esas coordenadas y lo añadimos a la lista
        }
        return lista; //Devolvemos la lista
    }

    public List<Punto> generarPuntosAleatoriosCasoPeor(){ 
        List<Punto> lista = new ArrayList<>(); 
        Random rnd = new Random();
        double x = rnd.nextDouble() * 100; 
        for (int i = 0; i < cantidad; i++) { //Utilizamos este bucle para crear la cantidad de puntos solicitada
            double y = rnd.nextDouble() * 100;
            lista.add(new Punto(x,y,i)); //Creamos el objeto 'Punto' con esas coordenadas y lo añadimos a la lista
        }
        return lista; //Devolvemos la lista
    }
}
