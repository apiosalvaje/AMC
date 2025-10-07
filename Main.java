import java.util.ArrayList; //Librería para crear listas dinámicasde objetos
import java.util.Random; //Librería que permite generar números aleatorios

public class Main {
    public static void main(String[] args){
        ArrayList<Punto> puntos = generarPuntosAleatorios(10); //Llamamos a la función 'generarPuntosAleatorios' para crear una lista de 10 puntos aleatorios
        for (Punto p : puntos){ //Este bucle recorre cada punto de la lista y lo muestra en consola
            System.out.println(p);
        }
        System.out.println("=== Método Exhaustivo ===");
        encontrarParMasCercano(puntos); //Llamamos al método que busca el par de puntos más cercanos entre todos los puntos generados, mostrando el resultado
        System.out.println("=== Método con Poda ===");
        encontrarParMasCercanoConPoda(puntos);
    }

    private static void buscarParMasCercano(ArrayList<Punto> puntos, boolean usarPoda) {

        if (puntos.size() < 2) { //Nos aseguramos de que haya más de 2 puntos en la lista
            System.out.println("Necesitas al menos dos puntos");
            return;
        }
        //Si usas poda, ordenamos primero por x
        if (usarPoda) {
            puntos.sort((pA, pB) -> Double.compare(pA.x, pB.x));
        }

        double distanciaMin = Double.MAX_VALUE;
        Punto p1 = null, p2 = null;

        for (int i = 0; i < puntos.size() - 1; i++){
            for (int j = i + 1; j < puntos.size(); j++){
                if (usarPoda) {
                    //Condición para podar
                    if ((puntos.get(j).x - puntos.get(i).x) >= distanciaMin) {
                        break;
                    }
                }
                double d = Distancia(puntos.get(i), puntos.get(j));
                if (d < distanciaMin) {
                    distanciaMin = d;
                    p1 = puntos.get(i);
                    p2 = puntos.get(j);
                }
            } 
        }
        System.out.println("Par más cercano: " + p1 + " y " + p2);
        System.out.println("Distancia mínima: " + distanciaMin);
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

    public static double Distancia(Punto a, Punto b){ //Este método calcula y devuelve la distancia euclidiana entre dos puntos dados
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy); //Formula: Raiz de la suma de los cuadrados
    }

    public static void encontrarParMasCercano(ArrayList<Punto> puntos){
        buscarParMasCercano(puntos, false);
        
    }

    public static void encontrarParMasCercanoConPoda(ArrayList<Punto> puntos){
        buscarParMasCercano(puntos, true);
    }
}
