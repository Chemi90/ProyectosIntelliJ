package com.example.gestionpedidos;

import lombok.Data;

import java.util.Date;

@Data
public class Pedidos {
    private String codigoPedido;
    private Date fecha;
    private Double Total;
}
