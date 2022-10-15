package br.com.buybye.port;

import br.com.buybye.adapter.database.entities.DetalhePedidoEntity;

import java.util.List;

public interface DetalhePedidoPort {

    List<DetalhePedidoEntity> findAll();
    DetalhePedidoEntity get(long  id);
    void save(DetalhePedidoEntity order);
    void delete(long id);
}
