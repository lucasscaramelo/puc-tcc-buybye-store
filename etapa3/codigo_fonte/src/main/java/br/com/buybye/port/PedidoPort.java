package br.com.buybye.port;

import br.com.buybye.adapter.database.entities.CarrinhoEntity;
import br.com.buybye.adapter.database.entities.CompradorEntity;
import br.com.buybye.adapter.database.entities.PedidoEntity;

import java.util.List;

public interface PedidoPort {
    List<PedidoEntity> findAllOrders(CompradorEntity customer);
    PedidoEntity saveOrder(CarrinhoEntity shoppingCart);
    PedidoEntity get(long  id);
    void delete(long id);
}
