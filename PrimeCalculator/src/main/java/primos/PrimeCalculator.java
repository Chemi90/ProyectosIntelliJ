package primos;

import java.util.ArrayList;

public class PrimeCalculator {

    public static Boolean isPrime(Integer numero) {
        for (int i = 2; i < (numero - 1); i++) {
            if (numero % i == 0) return false;
        }
        return true;
    }

    public static ArrayList<Integer> inRange(Integer inf, Integer sup) {
        ArrayList<Integer> salida = new ArrayList<>();
        for (Integer n = inf; n < sup; n++) {
            if (isPrime(n)) salida.add(n);
        }
        return salida;
    }

    public static Integer next(Integer i) {
        Integer n = i + 1;
        while (!isPrime(n)) n++;
        return n;
    }

    public static ArrayList<Integer> next(Integer i, Integer quantity) {
        var salida = new ArrayList<Integer>();
        Integer n = i + 1;
        for (int s = 0; s < quantity; s++) {
            while (!isPrime(n)) n++;
            salida.add(n);
            n++;
        }
        return salida;
    }
}