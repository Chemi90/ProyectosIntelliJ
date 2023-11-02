package com.example.gestionpedidoscondao.persistence;

import com.example.gestionpedidoscondao.model.ItemPedido;

import java.util.List;

public interface ItemPedidoDAO {
    List<ItemPedido> findItemsByPedidoCodigo(String pedidoId);
}
