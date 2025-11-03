package Datos;

public class DistanciaEuc {
    public int calculo = 0;

    public double calcula(Punto a, Punto b){
        calculo++; //Es el contador que utilizamos para obtener el número total de cálculos realizados
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        double distancia = Math.sqrt(dx * dx + dy * dy);
        return distancia; 
    }
}
