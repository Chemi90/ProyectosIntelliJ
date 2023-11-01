package com.example.gestionpedidoscondao.persistence;

import com.example.gestionpedidoscondao.model.Producto;

import java.util.List;

public interface ProductoDAO {
    List<Producto> findAll();
}
