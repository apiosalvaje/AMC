import java.util.*;

import Algoritmos.*;
import Datos.*;

public class Main {
    public void main(String[] args){
        int opcion;
        Scanner sc = new Scanner(System.in);

        List<Punto> puntos = Dataset.generarPuntosAleatorios(); //Llamamos a la función 'generarPuntosAleatorios' para crear una lista de 10 puntos aleatorios
        for (Punto p : puntos){ //Este bucle recorre cada punto de la lista y lo muestra en consola
            System.out.println(p);
        }

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
                    dataset = Dataset.generarPuntosAleatorios();
                    System.out.println("Pon el nombre del dataset");
                    String nombre = sc.next();
                    Dataset.GuardarDataset(nombre, dataset);
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

                    switch (data) {
                        case 1:
                            
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
                            System.out.println("\nVuelve pronto");
                            break;
                    
                        default:
                            System.out.println("\nNúmero equivocado");
                            break;
                    }
                    break;
                case 3:
                    int estrategia;
                        System.out.println("\n=== Comparaciones de Estrategias ===");
                        System.out.println("\n1. Exhaustivo");
                        System.out.println("\n2. Con Poda");
                        System.out.println("\n3. DivideYVenceras");
                        System.out.println("\n4. DivideYVencerasOpt");
                        System.out.println("\n0. Volver");
                        System.out.println("\nElige una estrategia");
                        estrategia = sc.nextInt();

                        switch (estrategia) {
                            case 1:
                                System.out.println("\n=== Método Exhaustivo ===");
                                Exhaustivo;

                                break;
                            case 2:
                                System.out.println("\n=== Método con Poda ===");
                                ConPoda.encontrarParMasCercanoConPoda(puntos);
                                
                                break;
                            case 3:
                                System.out.println("\n=== Método Divide y Vencerás ===");
                                Algoritmo.encontrarParMasCercanoDivideYVenceras(puntos);
                                
                                break;
                            case 4:
                                System.out.println("\n=== Método Divide y Vencerás Optimizado ===");
                                Algoritmo.encontrarParMasCercanoDivideYVencerasOpt(puntos.toArray(new Punto[0]));
                                
                                break;
                            case 0:
                                System.out.println("\nVuelve pronto!");
                                break;
                        
                            default:
                                System.out.println("\nNúmero equivocado");
                                break;
                        }
                    break;
                case 4:
                    System.out.println("\n=== Método Exhaustivo ===");
                    Exhaustivo.buscarParMasCercano(puntos, false); 
                    System.out.println("\n=== Método con Poda ===");
                    ConPoda.encontrarParMasCercanoConPoda(puntos);
                    System.out.println("\n=== Método Divide y Vencerás ===");
                    Algoritmo.encontrarParMasCercanoDivideYVenceras(puntos);
                    System.out.println("\n=== Método Divide y Vencerás Optimizado ===");
                    Algoritmo.encontrarParMasCercanoDivideYVencerasOpt(puntos.toArray(new Punto[0]));

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
