package com.example.gestionpedidoscondao.model;

import java.util.Date;

public class Pedido {
    private int id_pedidos;
    private String código;
    private Date fecha;
    private int usuario;
    private double total;

    public int getId_pedidos() {
        return id_pedidos;
    }

    public void setId_pedidos(int id_pedidos) {
        this.id_pedidos = id_pedidos;
    }

    public String getCódigo() {
        return código;
    }

    public void setCódigo(String código) {
        this.código = código;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Pedido(int id, String codigo, Date fecha, int usuarioId, double total) {
        this.id_pedidos = id;
        this.código = codigo;
        this.fecha = fecha;
        this.usuario = usuarioId;
        this.total = total;
    }

    public Pedido() {
    }
}