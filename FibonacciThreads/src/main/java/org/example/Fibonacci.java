package org.example;

public class Fibonacci extends Thread {
    private int n;
    private int fibn, fibn_1;

    public Fibonacci(int n) {
        this.n = n;
    }

    public int getFibn() {
        return fibn;
    }

    public int getFibn_1() {
        return fibn_1;
    }

    @Override
    public void run() {
        if (n <= 2) {
            fibn = 1;
            fibn_1 = 1;
            System.out.println("Fibonacci de " + n + ": " + fibn);
        } else {
            Fibonacci hebra = new Fibonacci(n - 1);
            hebra.start();
            while (hebra.isAlive()) ;
            fibn_1 = hebra.getFibn();
            fibn = fibn_1 + hebra.getFibn_1();
            System.out.println("Fibonacci de " + n + ": " + fibn);
        }
    }
}
