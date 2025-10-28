import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

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

    public static void encontrarParMasCercanoDivideYVenceras(ArrayList<Punto> puntos) {
        ArrayList<Punto> puntosOrdenadosX = new ArrayList<>(puntos);
        puntosOrdenadosX.sort(Comparator.comparingDouble(p -> p.x));
        ArrayList<Punto> puntosOrdenadosY = new ArrayList<>(puntos);
        puntosOrdenadosY.sort(Comparator.comparingDouble(p -> p.y));

        ResultadoPar resultado = dividirYVencer(puntosOrdenadosX, puntosOrdenadosY);
        System.out.println("Par más cercano (Divide y vencerás): " + resultado.p1 + " y " + resultado.p2);
        System.out.println("Distancia mínima (Divide y vencerás): " + resultado.distancia);
    }

    private static ResultadoPar dividirYVencer(ArrayList<Punto> ptsX, ArrayList<Punto> ptsY) {
        int n = ptsX.size();
        if (n <= 3) {
            return resultadoExhaustivo(ptsX.toArray(new Punto[0]));
        }
        int mid = n / 2;
        Punto mitad = ptsX.get(mid);

        ArrayList<Punto> izquierdaY = new ArrayList<>();
        ArrayList<Punto> derechaY = new ArrayList<>();
        for (Punto p : ptsY) {
            if (p.x <= mitad.x) izquierdaY.add(p);
            else derechaY.add(p);
        }

        ArrayList<Punto> izquierdaX = new ArrayList<>(ptsX.subList(0, mid));
        ArrayList<Punto> derechaX = new ArrayList<>(ptsX.subList(mid, n));

        ResultadoPar resultadoIzq = dividirYVencer(izquierdaX, izquierdaY);
        ResultadoPar resultadoDer = dividirYVencer(derechaX, derechaY);

        ResultadoPar menor = resultadoIzq.distancia < resultadoDer.distancia ? resultadoIzq : resultadoDer;
        double dmin = menor.distancia;

        ArrayList<Punto> franja = new ArrayList<>();
        for (Punto p : ptsY) {
            if (Math.abs(p.x - mitad.x) < dmin) franja.add(p);
        }
        ResultadoPar resultadoFranja = compararFranja(franja, dmin, menor);

        return resultadoFranja.distancia < menor.distancia ? resultadoFranja : menor;
    }

    private static ResultadoPar resultadoExhaustivo(Punto[] pts) {
        double minDist = Double.MAX_VALUE;
        Punto p1 = null, p2 = null;
        for (int i = 0; i < pts.length - 1; i++) {
            for (int j = i + 1; j < pts.length; j++) {
                double d = distancia(pts[i], pts[j]);
                if (d < minDist) {
                    minDist = d;
                    p1 = pts[i];
                    p2 = pts[j];
                }
            }
        }
        return new ResultadoPar(p1, p2, minDist);
    }

    private static ResultadoPar compararFranja(ArrayList<Punto> franja, double dmin, ResultadoPar menorActual) {
        double minDist = menorActual.distancia;
        Punto p1 = menorActual.p1, p2 = menorActual.p2;
        for (int i = 0; i < franja.size() - 1; i++) {
            for (int j = i + 1; j < franja.size() && (franja.get(j).y - franja.get(i).y) < minDist; j++) {
                double d = distancia(franja.get(i), franja.get(j));
                if (d < minDist) {
                    minDist = d;
                    p1 = franja.get(i);
                    p2 = franja.get(j);
                }
            }
        }
        return new ResultadoPar(p1, p2, minDist);
    }

    
    public static void encontrarParMasCercano(ArrayList<Punto> puntos){
        buscarParMasCercano(puntos, false);
        
    }

    public static void encontrarParMasCercanoConPoda(ArrayList<Punto> puntos){
        buscarParMasCercano(puntos, true);
    }

    public static void encontrarParMasCercanoDivideYVencerasOpt(Punto[] puntos) {
        Punto[] ptsX = puntos.clone();
        Arrays.sort(ptsX, Comparator.comparingDouble(p -> p.x));
        
        Punto[] ptsY = puntos.clone();
        Arrays.sort(ptsY, Comparator.comparingDouble(p -> p.y));
        
        ResultadoPar resultado = divideYVencerasOpt(ptsX, ptsY, 0, puntos.length);
        
        System.out.println("Par más cercano (Divide y vencerás optimizado): " + resultado.p1 + " y " + resultado.p2);
        System.out.println("Distancia mínima (Divide y vencerás optimizado): " + resultado.distancia);
    }

    private static ResultadoPar divideYVencerasOpt(Punto[] ptsX, Punto[] ptsY, int inicio, int fin) {
        int n = fin - inicio;
        if (n <= 3) {
            return resultadoExhaustivo(Arrays.copyOfRange(ptsX, inicio, fin));
        }

        int mid = inicio + n / 2;
        Punto mitad = ptsX[mid];

        // array para dividir ptsY en izquierdaY y derechaY sin crear muchas listas nuevas
        Punto[] izquierdaY = new Punto[mid - inicio];
        Punto[] derechaY = new Punto[fin - mid];
        int li = 0, ri = 0;
        for (int i = 0; i < ptsY.length; i++) {
            if (ptsY[i].x <= mitad.x && li < izquierdaY.length)
                izquierdaY[li++] = ptsY[i];
            else if (ri < derechaY.length)
                derechaY[ri++] = ptsY[i];
        }
        
        ResultadoPar resultadoIzq = divideYVencerasOpt(ptsX, izquierdaY, inicio, mid);
        ResultadoPar resultadoDer = divideYVencerasOpt(ptsX, derechaY, mid, fin);

        ResultadoPar menor = resultadoIzq.distancia < resultadoDer.distancia ? resultadoIzq : resultadoDer;
        double dmin = menor.distancia;

        // Construir franja central ordenada por Y sin crear demasiadas copias
        Punto[] franja = new Punto[n];
        int fi = 0;
        for (int i = 0; i < ptsY.length; i++) {
            if (Math.abs(ptsY[i].x - mitad.x) < dmin) {
                franja[fi++] = ptsY[i];
            }
        }

        // Comparar la franja sólo con los próximos 7 siguiendo orden Y
        for (int i = 0; i < fi; i++) {
            for (int j = i + 1; j < fi && (franja[j].y - franja[i].y) < dmin; j++) {
                double dist = distancia(franja[i], franja[j]);
                if (dist < dmin) {
                    dmin = dist;
                    menor = new ResultadoPar(franja[i], franja[j], dist);
                }
            }
        }

        return menor;
    }
}
