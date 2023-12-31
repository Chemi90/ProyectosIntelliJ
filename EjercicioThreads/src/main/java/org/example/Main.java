package org.example;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread hiloA = new Thread(new Hilo('A', 10));
        Thread hiloB = new Thread(new Hilo('B', 10));
        Thread hiloC = new Thread(new Hilo('C', 10));

        hiloA.start();
        hiloB.start();
        hiloC.start();

        hiloA.join();
        hiloB.join();
        hiloC.join();
    }
}
/*
Al ejecutarlo me doy cuenta que se ejecutan en orden los hilos en un principio
pero llega un momento que terminan de forma totalmente aleatoria.
 */