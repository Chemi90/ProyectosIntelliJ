package com.example.gestionpedidoscondao.persistence;

import com.example.gestionpedidoscondao.model.Usuario;

import java.util.List;

public interface UsuarioDAO {
    List<Usuario> findAll();
    boolean insert(Usuario usuario);
    boolean update(Usuario usuario);
    boolean delete(int id);
}