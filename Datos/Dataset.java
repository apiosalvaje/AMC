package Datos;
import java.io.*;
import java.util.*;

import Algoritmos.Quicksort;

public class Dataset {
    public static int cantidad = 100;

    public static List<Punto> generarPuntosAleatorios(){ //Este método:
        int cantidad = 100;
        List<Punto> lista = new ArrayList<>(); //Crea una lista vacía de puntos
        Random rnd = new Random();
        for (int i = 0; i < cantidad; i++) { //Utilizamos este bucle para crear la cantidad de puntos solicitada
            double x = rnd.nextDouble() * 100; //Para cada punto, generamos dos números aleatorios (x,y), ambos entre 0 y 100
            double y = rnd.nextDouble() * 100;
            lista.add(new Punto(x, y,i)); //Creamos el objeto 'Punto' con esas coordenadas y lo añadimos a la lista
        }
        return lista; //Devolvemos la lista
    }

    public static List<Punto> generarPuntosAleatoriosCasoPeor(){ 
        List<Punto> lista = new ArrayList<>(); 
        Random rnd = new Random();
        double x = rnd.nextDouble() * 100; 
        for (int i = 0; i < cantidad; i++) { //Utilizamos este bucle para crear la cantidad de puntos solicitada
            double y = rnd.nextDouble() * 100;
            lista.add(new Punto(x,y,i)); //Creamos el objeto 'Punto' con esas coordenadas y lo añadimos a la lista
        }
        return lista; //Devolvemos la lista
    }

    public static void GuardarDataset(String nombreArchivo, List<Punto> lista){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo + ".tsp"))){
            bw.write("NAME: " + nombreArchivo);
            bw.newLine();
            bw.write("TYPE : TSP");
            bw.newLine();
            bw.write("DIMENSION: " + lista.size());
            bw.newLine();
            bw.write("EDGE_WEIGHT_TYPE: GEO");
            bw.newLine();
            bw.write("NODE_COORD_SECTION");
            bw.newLine();

            for (Punto p : lista) {
                bw.write(p.getID() + " " + p.getX() + " " + p.getY());
                bw.newLine();
            }
            bw.write("EOF");
            bw.newLine();

            System.out.println("Archivo " + nombreArchivo + ".tsp guardado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar archivo");
        }
    }

    public static List<Punto> leerFicheros(String nombreArchivo) throws IOException {
        List<Punto> lecturaDataset = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))){
            String linea;
            int dimension = - 1;
            boolean leerDatos = false;

            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;
                
                if (linea.startsWith("DIMENSION")) {
                    String[] partes = linea.split(":");
                    if (partes.length > 1) {
                        dimension = Integer.parseInt(partes[1].trim());
                    }
                }
                else if (linea.startsWith("NODE_COORD_SECTION")) {
                    leerDatos = true;
                    continue;
                }

                if (leerDatos) {
                    if (linea.equalsIgnoreCase("EOF")) break;
                        
                    String[] tokens = linea.split("\\s+");
                    if (tokens.length >= 3) {
                        int ID =  Integer.parseInt(tokens[0]);
                        double x = Double.parseDouble(tokens[1]);
                        double y = Double.parseDouble(tokens[2]);

                        lecturaDataset.add(new Punto(x, y, ID));
                    }
                }

                }
            }catch (IOException e){
                System.err.println("Error al leer el fichero: " + e.getMessage());
            }
            
        return lecturaDataset;
    }

    public static void OrdenarDataset(List<Punto> lista){
        Quicksort.Ordena(lista);
    }

    public static List<Punto> copia(List<Punto> dataset){
        return new ArrayList<>(dataset);
    }
}
