package com.example.gestionpedidoscondao.model;

import lombok.*;

/**
 * Clase que representa un producto dentro del inventario del sistema.
 * <p>
 * Esta clase es responsable de mantener la información relevante para los productos
 * que pueden ser pedidos o gestionados a través del sistema. Incluye detalles como
 * el identificador único, el nombre del producto, su precio y la cantidad disponible.
 * </p>
 * <p>
 * Las anotaciones de Lombok se utilizan aquí para reducir el código repetitivo de getters,
 * setters y constructores que típicamente se necesitarían.
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
public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int cantidadDisponible;
}