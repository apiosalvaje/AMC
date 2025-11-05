package Datos;
public class Punto {
    public int ID;  //Es el identificador del punto
    public double x;  //Es la coordenada x
    public double y;  //Es la coordenada y


    public Punto(double x, double y, int ID){
        this.ID = ID; //Inicializamos ID
        this.x = x; //Inicializamos la coordenada x
        this.y = y; //Inicializamos la coordenada y
    }

    public int getID(){
        return ID; //Devolvemos el identificador del punto
    }

    public double getX(){
        return x; //Devolvemos la coordenada x
    }

    public double getY(){
        return y; //Devolvemos la coordenada y
    }

    public String toString(){
        return "(" + x + "," + y +")"; //Esta es la representación en texto del punto
    }
}