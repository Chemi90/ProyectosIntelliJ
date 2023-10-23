package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class hilo implements Runnable {

    private List<Integer> numbers;
    private Random random = new Random();
    private List<Integer> combinedNumbers; // ArrayList compartido

    public hilo(List<Integer> combinedNumbers) {
        this.numbers = new ArrayList<>();
        this.combinedNumbers = combinedNumbers;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            int randomNumber = random.nextInt(100);
            numbers.add(randomNumber);
        }
        // Sincroniza el acceso al ArrayList compartido
        synchronized (combinedNumbers) {
            combinedNumbers.addAll(numbers);
        }

        System.out.println(Thread.currentThread().getName() + ": " + numbers);
    }
}