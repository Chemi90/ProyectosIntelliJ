package com.example.gestionpedidoscondao.model;

import lombok.*;

/**
 * Clase que representa un ítem individual dentro de un pedido.
 * <p>
 * Contiene información sobre la cantidad de producto ordenado, el nombre del producto,
 * y el precio total para la cantidad específica de ese producto en el pedido.
 * Las anotaciones de Lombok simplifican la creación de métodos y constructores básicos.
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
public class ItemPedido {
    private int id;
    private String codPedido;
    private int cantidad;
    private String productoNombre;
    private Double precio;
}