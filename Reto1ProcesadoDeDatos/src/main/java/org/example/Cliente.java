package org.example;

import lombok.Data;

@Data
public class Cliente {

    private String codigoCliente;
    private String nombreEmpresa;
    private String localidad;
    private String correoElectronico;
    private String nombreResponsable;
}
