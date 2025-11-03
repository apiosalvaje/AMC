public class DistanciaEc {
    
    public static double distancia(Punto a, Punto b){
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
