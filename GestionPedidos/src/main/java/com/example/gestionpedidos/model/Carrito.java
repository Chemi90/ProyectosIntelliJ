package com.example.gestionpedidos.model;

public class Carrito {
    private String nombre;
    private int cantidad;
    private double precioTotal;

    public Carrito(String nombre, int cantidad, double precioTotal) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }
}

