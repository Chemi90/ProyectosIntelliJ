package org.example;

public class Hebra1 implements Runnable{

    private VariableCompartida v;

    public Hebra1(VariableCompartida v) {
        this.v = v;
    }

    @Override
    public void run() {
        for (int i = 0; i<=99;i++){
            synchronized (v) {
                v.notify();
                v.setV(i);
                System.out.println("Valor de v: " + v.getV());
                try {
                    v.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}