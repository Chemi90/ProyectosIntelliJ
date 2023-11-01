package com.example.gestionpedidos.model;

public class ItemPedido {
    private int idItems;
    private String codigoPedido;
    private int cantidad;
    private int idProducto;
    private String nombreProducto;

    public ItemPedido(int idItems, String codigoPedido, int cantidad, int idProducto, String nombreProducto) {
        this.idItems = idItems;
        this.codigoPedido = codigoPedido;
        this.cantidad = cantidad;
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
    }

    public int getIdItems() {
        return idItems;
    }

    public void setIdItems(int idItems) {
        this.idItems = idItems;
    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
}
