package com.example.gestionpedidoscondao.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un carrito de compras que contiene una lista de ítems de pedido.
 * Se utiliza para mantener y manipular la colección de ítems seleccionados por un usuario.
 * Las anotaciones de Lombok se utilizan aquí para proporcionar automáticamente
 * los métodos getter, setter, así como los constructores necesarios.
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
public class Carrito {
    private List<ItemPedido> items;
}