package org.example;
public class Hilo implements Runnable {

    private final char letra;
    private final int repeticiones;

    public Hilo(char letra, int repeticiones) {
        this.letra = letra;
        this.repeticiones = repeticiones;
    }

    @Override
    public void run() {
        for (int i = 0; i < repeticiones; i++) {
            System.out.print(letra);
        }
    }
}
