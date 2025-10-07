import java.util.ArrayList;

public class Algoritmo {
    public static double distancia(Punto a, Punto b){
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static void buscarParMasCercano(ArrayList<Punto> puntos, boolean usarPoda) {

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
                double d = distancia(puntos.get(i), puntos.get(j));
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
}
