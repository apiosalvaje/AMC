package Datos;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import Algoritmos.Quicksort;

public class Dataset {

    public static List<Punto> generarPuntosAleatorios(int cantidad){ 
        List<Punto> lista = new ArrayList<>(); //Creamos una lista vacía de puntos
        Random rnd = new Random();
        for (int i = 0; i < cantidad; i++) { //Utilizamos este bucle para crear la cantidad de puntos solicitada
            double x = rnd.nextDouble() * 100; //Para cada punto, generamos dos números aleatorios (x,y), ambos entre 0 y 100
            double y = rnd.nextDouble() * 100; //Para cada punto, generamos dos números aleatorios (x,y), ambos entre 0 y 100

            //Ajustamos la precisión para que los puntos tengan 10 decimales
            x = new BigDecimal(x).setScale(10, RoundingMode.HALF_UP).doubleValue();
            y = new BigDecimal(y).setScale(10, RoundingMode.HALF_UP).doubleValue();

            lista.add(new Punto(x, y,i)); //Añadimos punto con ID i a la lista
        }
        return lista; //Devolvemos la lista
    }

    public static List<Punto> generarPuntosAleatoriosCasoPeor(int cantidad){ 
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

            //Escribimos la cabecera del archivo
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

            //Escribimos cada punto con su ID y sus coordenadas
            for (Punto p : lista) {
                bw.write(p.getID() + " " + p.getX() + " " + p.getY());
                bw.newLine();
            }
            bw.write("EOF"); //Marcamos el final del archivo
            bw.newLine();

            System.out.println("Archivo " + nombreArchivo + ".tsp guardado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar archivo");
        }
    }

    public static List<Punto> leerFicheros(String nombreArchivo) throws IOException {
        List<Punto> lecturaDataset = new ArrayList<>(); //Esta es la lista donde se cargarán los puntos
        int dimension = - 1;
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))){ //Abrimos el archivo para la lectura
            String linea;
            boolean leerDatos = false; //Indicamos si ya se pueden leer los puntos

            while ((linea = br.readLine()) != null) { //Leemos línea a línea
                linea = linea.trim(); //Eliminamos espacios en blanco al inicio y al final
                if (linea.isEmpty()) continue; //Ignoramos las líneas vacías
                
                if (linea.startsWith("DIMENSION")) {
                    String[] partes = linea.split(":");
                    if (partes.length > 1) {
                        dimension = Integer.parseInt(partes[1].trim());
                    }
                }
                else if (linea.startsWith("NODE_COORD_SECTION")) { //Marca el inicio de los datos
                    leerDatos = true; //Empezamos a leer datos
                    continue;
                }

                if (leerDatos) {
                    if (linea.equalsIgnoreCase("EOF")) break; //Sería el final del archivo y de la lectura
                        
                    String[] tokens = linea.split("\\s+"); //Separamos valores
                    if (tokens.length >= 3) {
                        int ID =  Integer.parseInt(tokens[0]); //Esta es la ID del punto
                        double x = Double.parseDouble(tokens[1]); //Esta es la coordenada x del punto
                        double y = Double.parseDouble(tokens[2]); //Esta es la coordenada y del punto

                        lecturaDataset.add(new Punto(x, y, ID)); //Creamos objeto y añadimos
                    }
                }

                }
            }catch (IOException e){ //Aquí manejamos las excepciones por si falla alguna lectura
                System.err.println("Error al leer el fichero: " + e.getMessage());
            }
            //Verificamos que el número de puntos leídos, coincide con la dimensión esperada
            if (dimension != -1 && lecturaDataset.size() != dimension) {
                System.err.println("Cuidado: el número de puntos leídos (" + lecturaDataset.size() +
                ") no está coincidiendo con la DIMENSION esperada (" + dimension + ")");
            }
            
        return lecturaDataset; //Devolvemos la lista con los puntos leídos
    }

    public static void OrdenarDataset(List<Punto> lista){
        Quicksort.Ordena(lista);
    }

    public static List<Punto> copia(List<Punto> dataset){
        return new ArrayList<>(dataset);
    }
}
