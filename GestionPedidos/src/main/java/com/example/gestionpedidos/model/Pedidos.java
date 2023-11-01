package com.example.gestionpedidos.model;

import lombok.Data;

import java.util.Date;

@Data
public class Pedidos {
    private String codigoPedido;
    private Date fecha;
    private Double Total;
}
