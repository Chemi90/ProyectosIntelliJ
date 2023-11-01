package com.example.gestionpedidoscondao.persistence;

import com.example.gestionpedidoscondao.model.Pedido;

import java.util.List;

public interface PedidoDAO {
    List<Pedido> findByUsuarioId(int usuarioId);
}
