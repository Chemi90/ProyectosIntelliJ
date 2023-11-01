package com.example.gestionpedidoscondao.model;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<ItemPedido> items;

    public Carrito() {
        this.items = new ArrayList<>();
    }

    public void addItem(ItemPedido item) {
        this.items.add(item);
    }

    public void removeItem(ItemPedido item) {
        this.items.remove(item);
    }

    public double getTotal() {
        // Aquí sumas el precio de todos los productos y lo retornas.
        return 0;
    }

    // Otros métodos útiles...
}