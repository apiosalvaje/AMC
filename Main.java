import java.util.ArrayList; //Librería para crear listas dinámicasde objetos
import java.util.Random; //Librería que permite generar números aleatorios

public class Main {
    public static void main(String[] args){
        ArrayList<Punto> puntos = Punto.generarPuntosAleatorios(10); //Llamamos a la función 'generarPuntosAleatorios' para crear una lista de 10 puntos aleatorios
        for (Punto p : puntos){ //Este bucle recorre cada punto de la lista y lo muestra en consola
            System.out.println(p);
        }
        System.out.println("=== Método Exhaustivo ===");
        encontrarParMasCercano(puntos); //Llamamos al método que busca el par de puntos más cercanos entre todos los puntos generados, mostrando el resultado
        System.out.println("=== Método con Poda ===");
        encontrarParMasCercanoConPoda(puntos);
    }

    public static double Distancia(Punto a, Punto b){ //Este método calcula y devuelve la distancia euclidiana entre dos puntos dados
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy); //Formula: Raiz de la suma de los cuadrados
    }

    public static void encontrarParMasCercano(ArrayList<Punto> puntos){
        Algoritmo.buscarParMasCercano(puntos, false);
        
    }

    public static void encontrarParMasCercanoConPoda(ArrayList<Punto> puntos){
        Algoritmo.buscarParMasCercano(puntos, true);
    }
}
