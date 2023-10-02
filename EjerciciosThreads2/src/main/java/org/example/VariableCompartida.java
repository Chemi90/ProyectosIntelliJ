package org.example;

public class VariableCompartida {
    private int v;

    public VariableCompartida(int v) {
        this.v = v;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public void incrementar(){
        v = v + 1;
    }
}