package org.example;

public class Main {
    /*
    Al principio con un numero bajo para incrementar no ocurre nada reseñable, solo que puede llegar a hacer algun
    salto, ahora, en el momento en el que aumentamos mucho el incremento, no llega a dar el numero final.
     */
    public static void main(String[] args) throws InterruptedException {

        VariableCompartida variable = new VariableCompartida(0);

        Thread hilo1 = new Thread(new  Hebra(variable));
        Thread hilo2 = new Thread(new Hebra(variable));

        hilo1.start();
        hilo2.start();

        hilo1.join();
        hilo2.join();
    }
}