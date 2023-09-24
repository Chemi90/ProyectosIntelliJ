package org.example;
import java.util.Scanner;
public class NumerosPrimos {
    public static boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int contarNumerosPrimosEnIntervalo(int inicio, int fin) {
        int contador = 0;
        for (int i = inicio; i <= fin; i++) {
            if (esPrimo(i)) {
                System.out.println(i);
                contador++;
            }
        }
        return contador;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int inicio;

        while (true) {
            System.out.print("Introduce el número de partida (o 0 para salir): ");
            inicio = sc.nextInt();

            if (inicio == 0) {
                System.out.println("Saliendo del programa...");
                break;
            }

            System.out.print("Introduce el fin del intervalo (entero): ");
            int fin = sc.nextInt();

            System.out.println("Buscando primos...");

            if (inicio > fin) {
                System.out.println("El inicio del intervalo debe ser menor o igual al fin del intervalo.");
            } else {
                int cantidadPrimos = contarNumerosPrimosEnIntervalo(inicio, fin);
                System.out.println("Cantidad de números primos en el intervalo: " + cantidadPrimos);
            }
        }
    }
}