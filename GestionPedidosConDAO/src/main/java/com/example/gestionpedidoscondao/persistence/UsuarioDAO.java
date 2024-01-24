package com.example.gestionpedidoscondao.persistence;

import com.example.gestionpedidoscondao.model.Usuario;

import java.util.List;

/**
 * Interfaz para definir las operaciones de acceso a datos relacionadas con los usuarios.
 * Proporciona métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Borrar)
 * sobre la entidad Usuario.
 *
 * @author José Miguel Ruiz Guevara
 * @version 1.0
 * @since 1.0
 */
public interface UsuarioDAO {

    /**
     * Recupera todos los usuarios existentes en la base de datos.
     *
     * @return una lista de objetos Usuario.
     */
    List<Usuario> findAll();

    /**
     * Inserta un nuevo usuario en la base de datos.
     *
     * @param usuario El objeto Usuario a insertar.
     * @return true si la operación de inserción es exitosa, false en caso contrario.
     */
    boolean insert(Usuario usuario);

    /**
     * Actualiza la información de un usuario existente en la base de datos.
     *
     * @param usuario El objeto Usuario con la información actualizada.
     * @return true si la operación de actualización es exitosa, false en caso contrario.
     */
    boolean update(Usuario usuario);

    /**
     * Elimina un usuario de la base de datos utilizando su identificador único.
     *
     * @param id El identificador del usuario a eliminar.
     * @return true si la operación de eliminación es exitosa, false en caso contrario.
     */
    boolean delete(int id);
}