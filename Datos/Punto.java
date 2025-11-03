package Datos;
public class Punto {
    public int ID;
    public double x;
    public double y;


    public Punto(double x, double y, int ID){
        this.ID = ID;
        this.x = x;
        this.y = y;
    }

    public int getID(){
        return ID;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public String toString(){
        return "(" + x + "," + y +")";
    }
}