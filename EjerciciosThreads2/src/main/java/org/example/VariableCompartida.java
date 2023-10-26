package org.example;

public class VariableCompartida {
    private int v;
    private boolean bool;

    public synchronized int getV(){
        while (!bool){
            try{
                wait();
            }catch (InterruptedException e){

            }
        }
        bool = false;
        notify();
        return v;
    }

    public VariableCompartida(int v) {
        this.v = v;
    }



    public synchronized  void setV(int v) {
        this.v = v;
    }

    public void incrementar(){
        v = v + 1;
    }
}