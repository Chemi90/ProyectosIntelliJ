package org.example;

public class Hebra2 implements Runnable {
    private VariableCompartida v;

    public Hebra2(VariableCompartida v) {
        this.v = v;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 99; i++) {
            synchronized (v) {
                v.notify();
                System.out.println("Valor de v HEBRA: " + v.getV());
                try {
                    if (i < 99) v.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
