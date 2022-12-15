package br.com.buybye.domain.usecase;

import br.com.buybye.adapter.database.entities.ItemCarrinhoEntity;
import br.com.buybye.adapter.database.repository.ItemCarrinhoRepository;
import br.com.buybye.port.ItemCarrinhoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCarrinhoUseCase implements ItemCarrinhoPort {

    @Autowired
    private ItemCarrinhoRepository cartItemRepository;

    @Override
    public List<ItemCarrinhoEntity> findAll() {
        return (List<ItemCarrinhoEntity>) cartItemRepository.findAll();
    }

    @Override
    public ItemCarrinhoEntity get(long id) {
        return cartItemRepository.findById(id).get();
    }

    @Override
    public void save(ItemCarrinhoEntity itemCarrinho) {
        cartItemRepository.save(itemCarrinho);
    }

    @Override
    public void delete(long id) {
        cartItemRepository.deleteById(id);
    }
}
