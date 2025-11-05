package Datos;

public class DistanciaEuc {
    public int calculo = 0;  //Este es el contador de cálculos de distancia realizados

    public double calcula(Punto a, Punto b){
        calculo++; //Aquí incrementamos el contador de cálculos de distancia
        double dx = a.x - b.x; //Diferencia en x
        double dy = a.y - b.y; //Diferencia en y
        double distancia = Math.sqrt(dx * dx + dy * dy); //Aquí calculamos la distancia euclídia
        return distancia; //Devolvemos la distancia
    }
}
