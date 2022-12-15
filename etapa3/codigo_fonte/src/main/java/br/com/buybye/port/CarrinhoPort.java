package br.com.buybye.port;

import br.com.buybye.adapter.database.entities.CarrinhoEntity;
import br.com.buybye.adapter.database.entities.CompradorEntity;
import br.com.buybye.adapter.database.entities.ItemCarrinhoEntity;
import br.com.buybye.adapter.database.entities.ProdutoEntity;

public interface CarrinhoPort {
    CarrinhoEntity findById(Long idCarrinho);
    ItemCarrinhoEntity findCartItem(CarrinhoEntity carrinho, Long idProduto);
    CarrinhoEntity findShoppingCart(CompradorEntity comprador);
    CarrinhoEntity addItemToCart(ProdutoEntity produto, Long qtd, CompradorEntity comprador);
    CarrinhoEntity removeItemFromCart(ProdutoEntity produto, CompradorEntity comprador);
    CarrinhoEntity updateItemFromCart(ProdutoEntity produto, Long qtd, CompradorEntity comprador);
    void emptyShoppingCart(CompradorEntity comprador);
}
