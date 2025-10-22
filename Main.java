import java.util.ArrayList; //Librería para crear listas dinámicasde objetos

public class Main {
    public static void main(String[] args){
        ArrayList<Punto> puntos = Punto.generarPuntosAleatorios(10); //Llamamos a la función 'generarPuntosAleatorios' para crear una lista de 10 puntos aleatorios
        for (Punto p : puntos){ //Este bucle recorre cada punto de la lista y lo muestra en consola
            System.out.println(p);
        }
        System.out.println("=== Método Exhaustivo ===");
        Algoritmo.encontrarParMasCercano(puntos); //Llamamos al método que busca el par de puntos más cercanos entre todos los puntos generados, mostrando el resultado
        System.out.println("=== Método con Poda ===");
        Algoritmo.encontrarParMasCercanoConPoda(puntos);
        System.out.println("=== Método Divide y Vencerás ===");
        Algoritmo.encontrarParMasCercanoDivideYVenceras(puntos);
    }
}
