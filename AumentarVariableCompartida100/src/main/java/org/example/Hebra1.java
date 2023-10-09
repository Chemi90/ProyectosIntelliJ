package org.example;

public class Hebra1 implements Runnable{

    private VariableCompartida v;

    public Hebra1(VariableCompartida v) {
        this.v = v;
    }

    @Override
    public void run() {
        for (int i = 0; i<=99;i++){
            v.setV(i);
            System.out.println("Valor de v: " + v.getV());
            try {
                // Simula un proceso que toma tiempo
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}