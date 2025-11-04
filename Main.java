import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import Algoritmos.*;
import Datos.*;

public class Main {
    public static void main(String[] args){
        int opcion;
        Scanner sc = new Scanner(System.in);
        String NDataset;
        List<Punto> DCargado = new ArrayList<>();
        List<Punto> DCargadoCopia = new ArrayList<>(DCargado);

        do {
            System.out.println("\n=== Menú Práctica 1 ===");
            System.out.println("\n=== 1. Crear un fichero .tsp aleatorio ===");
            System.out.println("\n=== 2. Cargar un dataset en memoria ===");
            System.out.println("\n=== 3. Comprobar Estrategias ===");
            System.out.println("\n=== 4. Comparar todas las estrategias ===");
            System.out.println("\n=== 5. Comparar 2 estrategias ===");
            System.out.println("\n=== 6. Generar dataset Caso Peor ===");
            System.out.println("\n=== 0. Salir ===");
            System.out.println("\nElige la opcion");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    List<Punto> dataset = new ArrayList<>();
                    System.out.println("Cuantos datos generar");
                    int N = sc.nextInt();
                    dataset = Dataset.generarPuntosAleatorios(N);
                    Dataset.GuardarDataset("dataset"+N, dataset);
                    break;
                case 2:
                    System.out.println("\n=== Dataset ===");
                    System.out.println("\n1. berlin52.tsp");
                    System.out.println("\n2. ch130.tsp");
                    System.out.println("\n3. ch150.tsp");
                    System.out.println("\n4. d493.tsp");
                    System.out.println("\n5. d657.tsp");
                    System.out.println("\n6. Otro dataset");
                    System.out.println("\n0. Volver");
                    System.out.println("\nElige el dataset que quieres cargar: ");
                    int data = sc.nextInt();

                    try{
                    switch (data) {
                        case 1:
                            DCargado = Dataset.leerFicheros("berlin52.tsp");
                            NDataset = "berlin52.tsp";
                            break;
                        case 2:
                            DCargado = Dataset.leerFicheros("ch130.tsp");
                            NDataset = "ch130.tsp";
                            break;
                        case 3:
                            DCargado = Dataset.leerFicheros("ch150.tsp");
                            NDataset = "ch150.tsp";
                            break;
                        case 4:
                            DCargado = Dataset.leerFicheros("d493.tsp");
                            NDataset = "d493.tsp";
                            break;
                        case 5:
                            DCargado = Dataset.leerFicheros("d657.tsp");
                            NDataset = "d657.tsp";
                            break;
                        case 6:
                            System.out.println("\nDime el dataset que quieres cargar: ");
                            NDataset = sc.next();
                            System.out.println("\nCargando el dataset " + NDataset);
                            if (Files.exists(Path.of(NDataset))) {
                                DCargado = Dataset.leerFicheros(NDataset);
                                break;
                            } else{
                                System.out.println("Dataset equivocado");
                                break;
                            }
                        case 0:
                            System.out.println("\nVuelve pronto");
                            break;
                    
                        default:
                            System.out.println("\nNúmero equivocado");
                            break;
                        }
                    }catch (IOException e){
                        System.err.println("Error al leer el fichero: " + e.getMessage());
                    }
                    break;
                case 3:
                        Algoritmo algoritmo;
                        double tiempo;
   
                        DCargadoCopia = Dataset.copia(DCargado);
                        Dataset.OrdenarDataset(DCargadoCopia);
                        System.out.println("\n=== Método Exhaustivo ===");
                        algoritmo = new Exhaustivo(DCargadoCopia);
                        Exhaustivo ex = new Exhaustivo(DCargadoCopia);
                        tiempo = ex.ejecutarConMedicion();
                        System.out.printf("Tiempo: %.4f ms%n", tiempo);
                        System.out.println("Distancia: " + algoritmo.MejorDis);
                        System.out.println("Punto1: " + algoritmo.ParMejor.get(0).ID  + algoritmo.ParMejor.get(0));
                        System.out.println("Punto2: " + algoritmo.ParMejor.get(1).ID  + algoritmo.ParMejor.get(1));
                        System.out.println("Calculadas: " + algoritmo.distanciacalculada());
                        
                        DCargadoCopia = Dataset.copia(DCargado);
                        Dataset.OrdenarDataset(DCargadoCopia);
                        System.out.println("\n=== Método con Poda ===");
                        algoritmo = new ConPoda(DCargadoCopia);
                        ConPoda cp = new ConPoda(DCargadoCopia);
                        tiempo = cp.ejecutarConMedicion();
                        System.out.printf("Tiempo: %.4f ms%n", tiempo);
                        System.out.println("Distancia: " + algoritmo.MejorDis);
                        System.out.println("Punto1: " + algoritmo.ParMejor.get(0).ID  + algoritmo.ParMejor.get(0));
                        System.out.println("Punto2: " + algoritmo.ParMejor.get(1).ID  + algoritmo.ParMejor.get(1));
                        System.out.println("Calculadas: " + algoritmo.distanciacalculada());
                        
                        DCargadoCopia = Dataset.copia(DCargado);
                        Dataset.OrdenarDataset(DCargadoCopia);
                        System.out.println("\n=== Método Divide y Vencerás ===");
                        algoritmo = new DivideYVenceras(DCargadoCopia);
                        DivideYVenceras DV = new DivideYVenceras(DCargadoCopia);
                        tiempo = DV.ejecutarConMedicion();
                        System.out.printf("Tiempo: %.4f ms%n", tiempo);
                        System.out.println("Distancia: " + algoritmo.MejorDis);
                        System.out.println("Punto1: " + algoritmo.ParMejor.get(0).ID  + algoritmo.ParMejor.get(0));
                        System.out.println("Punto2: " + algoritmo.ParMejor.get(1).ID  + algoritmo.ParMejor.get(1));
                        System.out.println("Calculadas: " + algoritmo.distanciacalculada());
                                
                        DCargadoCopia = Dataset.copia(DCargado);
                        Dataset.OrdenarDataset(DCargadoCopia);
                        System.out.println("\n=== Método Divide y Vencerás Optimizado ===");
                        algoritmo = new DivideYVencerasOpt(DCargadoCopia);
                        DivideYVencerasOpt DVO = new DivideYVencerasOpt(DCargadoCopia);
                        tiempo = DVO.ejecutarConMedicion();
                        System.out.printf("Tiempo: %.4f ms%n", tiempo);
                        System.out.println("Distancia: " + algoritmo.MejorDis);
                        System.out.println("Punto1: " + algoritmo.ParMejor.get(0).ID  + algoritmo.ParMejor.get(0));
                        System.out.println("Punto2: " + algoritmo.ParMejor.get(1).ID  + algoritmo.ParMejor.get(1));
                        System.out.println("Calculadas: " + algoritmo.distanciacalculada());
                        
                    break;
                case 4: 

                    int[] tallas = {1000, 2000, 3000, 4000, 5000};
                    int NExperimentos = 10;

                    System.out.println("Talla \t Exhaustivo \t ConPoda \t DivideYVenceras \t DivideYVencerasOpt");

                    for(int talla : tallas){
                        double sumExhaustivo = 0;
                        double sumConPoda = 0;
                        double sumDivide = 0;
                        double sumDivideOpt = 0;

                        for(int exp = 0; exp < NExperimentos; exp++){
                            DCargado = Dataset.generarPuntosAleatorios(talla);

                            DCargadoCopia = Dataset.copia(DCargado);
                            Dataset.OrdenarDataset(DCargadoCopia);
                            Exhaustivo ex1 = new Exhaustivo(DCargadoCopia);
                            sumExhaustivo += ex1.ejecutarConMedicion();
                        
                            DCargadoCopia = Dataset.copia(DCargado);
                            Dataset.OrdenarDataset(DCargadoCopia);
                            ConPoda cp1 = new ConPoda(DCargadoCopia);
                            sumConPoda += cp1.ejecutarConMedicion();
                        
                            DCargadoCopia = Dataset.copia(DCargado);
                            Dataset.OrdenarDataset(DCargadoCopia);
                            DivideYVenceras DV1 = new DivideYVenceras(DCargadoCopia);
                            sumDivide += DV1.ejecutarConMedicion();
                                
                            DCargadoCopia = Dataset.copia(DCargado);
                            Dataset.OrdenarDataset(DCargadoCopia);
                            DivideYVencerasOpt DVO1 = new DivideYVencerasOpt(DCargadoCopia);
                            sumDivideOpt += DVO1.ejecutarConMedicion();
                            }

                            System.out.printf("%d\t%.4f\t\t%.4f\t\t%.4f\t\t\t%.4f%n", talla, sumExhaustivo / NExperimentos, sumConPoda / NExperimentos, sumDivide / NExperimentos, sumDivideOpt / NExperimentos);
                        }
                    break;
                case 5:
                    int pareja;
                    do {
                        System.out.println("\n=== Comparaciones de Estrategias ===");
                        System.out.println("\n1. Exhaustivo vs ConPoda");
                        System.out.println("\n2. Exhaustivo vs DivideYVenceras");
                        System.out.println("\n3. Exhaustivo vs DivideYVencerasOpt");
                        System.out.println("\n4. ConPoda vs DivideYVenceras");
                        System.out.println("\n5. ConPoda vs DivideYVencerasOpt");
                        System.out.println("\n6. DivideYVenceras vs DivideYVencerasOpt");
                        System.out.println("\n0. Volver");
                        System.out.println("\nElige una pareja");
                        pareja = sc.nextInt();

                        switch (pareja) {
                            case 1:
                                //tallas = {1000, 2000, 3000, 4000, 5000};
                                NExperimentos = 10;

                                System.out.println("Talla \t Exhaustivo \t ConPoda \t Exhaustivo \t ConPoda");

                                /*for(int talla : tallas){
                                    double sumExhaustivo = 0;
                                    double sumConPoda = 0;
                                    double sumDivide = 0;
                                    double sumDivideOpt = 0;

                                    for(int exp = 0; exp < NExperimentos; exp++){
                                        DCargado = Dataset.generarPuntosAleatorios(talla);

                                        DCargadoCopia = Dataset.copia(DCargado);
                                        Dataset.OrdenarDataset(DCargadoCopia);
                                        Exhaustivo ex1 = new Exhaustivo(DCargadoCopia);
                                        sumExhaustivo += ex1.ejecutarConMedicion();
                                    
                                        DCargadoCopia = Dataset.copia(DCargado);
                                        Dataset.OrdenarDataset(DCargadoCopia);
                                        ConPoda cp1 = new ConPoda(DCargadoCopia);
                                        sumConPoda += cp1.ejecutarConMedicion();
                                    
                                        DCargadoCopia = Dataset.copia(DCargado);
                                        Dataset.OrdenarDataset(DCargadoCopia);
                                        DivideYVenceras DV1 = new DivideYVenceras(DCargadoCopia);
                                        sumDivide += DV1.ejecutarConMedicion();
                                            
                                        DCargadoCopia = Dataset.copia(DCargado);
                                        Dataset.OrdenarDataset(DCargadoCopia);
                                        DivideYVencerasOpt DVO1 = new DivideYVencerasOpt(DCargadoCopia);
                                        sumDivideOpt += DVO1.ejecutarConMedicion();
                                        }

                                    System.out.printf("%d\t%.4f\t\t%.4f\t\t%.4f\t\t\t%.4f%n", talla, sumExhaustivo / NExperimentos, sumConPoda / NExperimentos, sumDivide / NExperimentos, sumDivideOpt / NExperimentos);
                                }*/
                                
                                break;
                            case 2:
                                
                                break;
                            case 3:
                                
                                break;
                            case 4:
                                
                                break;
                            case 5:
                                
                                break;
                            case 6:
                                
                                break;
                            case 0:
                                System.out.println("\nVuelve pronto!");
                                break;
                        
                            default:
                            System.out.println("\nNúmero equivocado");
                                break;
                        }

                    } while (pareja != 0);
                    

                    break;
                case 6:
                    List<Punto> datasetP = new ArrayList<>();
                    System.out.println("Cuantos datos generar");
                    N = sc.nextInt();
                    datasetP = Dataset.generarPuntosAleatoriosCasoPeor(N);
                    Dataset.GuardarDataset("Caso Peor"+N, datasetP);
                    break;
                case 0:
                    System.out.println("\nGracias por la visita");
                    break;
            
                default:
                    System.out.println("\nNúmero equivocado");
                    break;
            }
            
        } while (opcion != 0);
        sc.close();

    }
}
