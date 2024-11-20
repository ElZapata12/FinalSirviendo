package ProyectoFinal;

import java.util.Scanner;

//Evelin Ramiro Rosales
//Maria Fernanda Gomez Gregorio
//Miguel Angel Zapata Rosales
public class Main {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("Gestion y Control de Transporte de Teziutlan:");
                System.out.println("1. Agregar Combi");
                System.out.println("4. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        Unidades.gestionarCola();
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } while (opcion != 4);
        }
}
