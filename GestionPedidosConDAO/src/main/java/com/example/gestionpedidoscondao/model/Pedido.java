package com.example.gestionpedidoscondao.model;

import lombok.*;

import java.util.Date;

/**
 * Clase que representa un pedido dentro del sistema de gestión.
 * <p>
 * Esta clase contiene toda la información relevante de un pedido realizado por un usuario.
 * Incluye el identificador del pedido, el código único, la fecha en que se realizó,
 * el usuario que hizo el pedido y el total del costo del pedido.
 * </p>
 * <p>
 * Se emplean anotaciones de Lombok para reducir la necesidad de código boilerplate
 * como getters y setters y constructores.
 * </p>
 *
 * @author José Miguel Ruiz Guevara
 * @version 1.0
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pedido {
    private int id_pedidos;
    private String código;
    private Date fecha;
    private int usuario;
    private double total;
}