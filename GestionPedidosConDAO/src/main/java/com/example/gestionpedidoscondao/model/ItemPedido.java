package com.example.gestionpedidoscondao.model;

public class ItemPedido {
    private int id;
    private int pedidoId;
    private int cantidad;
    private int productoId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public ItemPedido(int id, int pedidoId, int cantidad, int productoId) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.cantidad = cantidad;
        this.productoId = productoId;
    }

    public ItemPedido() {
    }
}