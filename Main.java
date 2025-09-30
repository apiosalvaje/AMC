import java.util.ArrayList;
import java.util.Random;    

public class Main {
    public static void main(String[] args){
        ArrayList<Punto> puntos = generarPuntosAleatorios(10); //10 puntos de ejemplo
        for (Punto p : puntos){
            System.out.println(p);
        }
    }

    public static ArrayList<Punto> generarPuntosAleatorios(int cantidad){
        ArrayList<Punto> lista = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < cantidad; i++) {
            double x = rnd.nextDouble() * 100; // RANGO 0-100
            double y = rnd.nextDouble() * 100;
            lista.add(new Punto(x, y));
        }
        return lista;
    }
}
