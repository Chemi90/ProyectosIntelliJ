package org.example;

public class Hebra implements Runnable{

    private VariableCompartida v;

    public Hebra(VariableCompartida v) {
        this.v = v;
    }

    @Override
    public void run() {
        for (int i = 1; i<=1000000;i++){
            v.incrementar();
            System.out.println(v.getV());
        }
    }
}
