import java.util.ArrayList;
import java.util.Random;

public class Punto {
    public double x;
    public double y;


    public Punto(double x, double y){
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return "(" + x + "," + y +")";
    }

    public static ArrayList<Punto> generarPuntosAleatorios(int cantidad){ //Este método:
        ArrayList<Punto> lista = new ArrayList<>(); //Crea una lista vacía de puntos
        Random rnd = new Random();
        for (int i = 0; i < cantidad; i++) { //Utiliza este bucle para crear la cantidad de puntos solicitada
            double x = rnd.nextDouble() * 100; //Para cada punto, genera dos números aleatorios (x,y), ambos entre 0 y 100
            double y = rnd.nextDouble() * 100;
            lista.add(new Punto(x, y)); //Crea el objeto 'Punto' con esas coordenadas y lo añade a la lista
        }
        return lista; //Devuelve la lista
    }

}