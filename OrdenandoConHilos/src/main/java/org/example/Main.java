package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> combinedNumbers = Collections.synchronizedList(new ArrayList<>());
        Thread thread1 = new Thread(new hilo(combinedNumbers));
        Thread thread2 = new Thread(new hilo(combinedNumbers));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Aquí puedes acceder al ArrayList combinado después de que los hilos hayan terminado
        Collections.sort(combinedNumbers);
        System.out.println("Números combinados: " + combinedNumbers);
    }
}