package com.example.gestionpedidoscondao.persistence;

import com.example.gestionpedidoscondao.model.Producto;

import java.util.List;


/**
 * Interfaz para definir las operaciones de acceso a datos relacionadas con los productos.
 * Esto incluiría operaciones CRUD estándar junto con cualquier otro comportamiento
 * específico relacionado con la entidad Producto en la aplicación.
 *
 * @author José Miguel Ruiz Guevara
 * @version 1.0
 * @since 1.0
 */
public interface ProductoDAO {

    /**
     * Recupera todos los productos de la base de datos.
     *
     * @return una lista de objetos {@link Producto} que representan todos los productos almacenados.
     */
    List<Producto> findAll();
}
