package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        VariableCompartida variable = new VariableCompartida(0);

        Thread hilo1 = new Thread(new Hebra1(variable));
        Thread hilo2 = new Thread(new Hebra2(variable));

        hilo1.start();
        hilo2.start();

        hilo1.join();
        hilo2.join();

    }
}
/*
El hilo que solo hace Get, pinta lo que le va apeteciendo, con notify y
wait llega a igualarse y muestra por pantalla lo que incrementa.
 */