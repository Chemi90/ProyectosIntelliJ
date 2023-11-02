package com.example.gestionpedidoscondao.model;

public class ItemPedido {
    private int id;
    private String pedidoId;
    private int cantidad;
    private int productoId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(String pedidoId) {
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

    public ItemPedido(int id, String pedidoId, int cantidad, int productoId) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.cantidad = cantidad;
        this.productoId = productoId;
    }

    public ItemPedido() {
    }
}