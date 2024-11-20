package ProyectoFinal;
import javax.swing.*;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;



public class Unidades {
    Random random = new Random();
    private int[] elementos;
    private HashMap<Integer, Integer> combis;
    private int frente;
    private int fin;
    private int tamanio;
    private int capacidad;
    private int totalRonda = 0;
    
    private static final String[] PARADAS = {
        "Base Teziutlán",
        "La palma",
        "Plan de Edén",
        "Bodega ahorrará",
        "Ahuateno",
        "San diego",
        "IES",
        "La estación",
        "Las tres cruces",
        "La misma",
        "La",
        "Amila",
        "Base de Tecnológico"
    };
    public void lugar() {
        System.out.println("Las combis se encuentran en:");
        for (Integer combi : combis.keySet()) {
            // Verificar si el ID de la combi está dentro del rango de paradas
            int puntosAcumulados=combis.get(combi);
            int paradaIndex=puntosAcumulados;
            if (paradaIndex >= 0 && paradaIndex < PARADAS.length) {
                System.out.println("Combi " + combi + " está en: " + PARADAS[paradaIndex]);
            } else {
                System.out.println("Combi " + combi + " tiene una parada no válida.");
            }
        }
    }


    public Unidades(int capacidad) {
        elementos = new int[capacidad];
        frente = 0;
        fin = -1;
        tamanio = 0;
        this.capacidad = capacidad;
        combis = new HashMap<>();
    }
    

    public boolean estaLlena() {
        return tamanio == capacidad;
    }

    public boolean estaVacia() {
        return tamanio == 0;
    }

    public void encolar(int elemento) {
        if (estaLlena()) {
            System.out.println("La cola está llena");
            return;
        }
        fin = (fin + 1) % elementos.length;
        elementos[fin] = elemento;
        combis.put(elemento, 0);
        tamanio++;
    }
    public int desencolar() {
        if (estaVacia()) {
            System.out.println("Aún no hay combis trabajando");
            return -1;
        }
        int elemento = elementos[frente];
        frente = (frente + 1) % elementos.length;
        tamanio--;
        return elemento;
    }

    public void simulacion() {
        int numRondas = 3;
        for (int ronda = 1; ronda <= numRondas; ronda++) {
            System.out.println("Ronda " + ronda + ":");
            int totalRonda=0;
            for (int i = 0; i < tamanio; i++) {
                int index = (frente + i) % elementos.length;
                int valorAleatorio = random.nextInt(3) + 1;
                totalRonda += valorAleatorio;
                int combiId = elementos[index];
                combis.put(combiId, combis.get(combiId) + valorAleatorio);
                System.out.println("Combi " + combiId + ", Valor Aleatorio = " + valorAleatorio);
                
            }
            
            System.out.println("Total acumulado de la ronda " + ronda + ": " + totalRonda);
            System.out.println("Total acumulado por combi:");
            for (Integer combi : combis.keySet()) {
                System.out.println("Combi " + combi + ": " + combis.get(combi));
            }
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Simulación de Combis");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 400);
                frame.add(new CircuitoPanel(combis));
                frame.setVisible(true);
            });
    
            System.out.print("Presione Enter para continuar a la siguiente ronda...");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();




            System.out.print("Presione Enter para continuar a la siguiente ronda...");
            Scanner sc = new Scanner(System.in);
            scanner.nextLine();
        }
        System.out.println("Simulación finalizada.");
    }
    public void mostrarCola() {
        if (estaVacia()) {
            System.out.println("Aún no hay combis trabajando.");
            return;
        }
        System.out.print("Combis Trabajando (" + tamanio + "/" + capacidad + "): ");
        for (int i = 0; i < tamanio; i++) {
            int index = (frente + i) % elementos.length;
            System.out.print(elementos[index] + " ");
        }
        System.out.println();
    }

    public static void gestionarCola() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Combis dadas de alta: ");
        int capacidad = sc.nextInt();
        Unidades unidades = new Unidades(capacidad);

        int opcion;
        do {
            System.out.println("\nMenú Cola de Boletos:");
            System.out.println("1. Empezar recorrido de combi");
            System.out.println("2. Termino de recorrido de combi");
            System.out.println("3. Mostrar Combis en recorrido");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    if (unidades.estaLlena()) {
                        System.out.println("Ya están todas las combis en el recorrido, empezando simulación...");
                        unidades.simulacion();
                    } else {
                        System.out.print("No. de Combi: ");
                        int cliente = sc.nextInt();
                        unidades.encolar(cliente);
                        System.out.println("Combi " + cliente + " en recorrido.");
                    }
                    break;
                case 2:
                    int desencolado = unidades.desencolar();
                    if (desencolado != -1) {
                        System.out.println("La combi " + desencolado + " ha llegado a la base.");
                    }
                    break;
                case 3:
                    unidades.mostrarCola();
                    unidades.lugar();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    public static void main(String[] args) {
        gestionarCola();
    }
}