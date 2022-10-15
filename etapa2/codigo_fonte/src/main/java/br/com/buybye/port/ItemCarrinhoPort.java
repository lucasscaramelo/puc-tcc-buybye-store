package br.com.buybye.port;

import br.com.buybye.adapter.database.entities.ItemCarrinhoEntity;

import java.util.List;

public interface ItemCarrinhoPort {
    List<ItemCarrinhoEntity> findAll();
    ItemCarrinhoEntity get(long id);
    void save(ItemCarrinhoEntity itemCarrinho);
    void delete(long id);
}
