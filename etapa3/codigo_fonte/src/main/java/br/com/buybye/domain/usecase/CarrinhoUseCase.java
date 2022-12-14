package br.com.buybye.domain.usecase;

import br.com.buybye.adapter.database.entities.CarrinhoEntity;
import br.com.buybye.adapter.database.entities.CompradorEntity;
import br.com.buybye.adapter.database.entities.ItemCarrinhoEntity;
import br.com.buybye.adapter.database.entities.ProdutoEntity;
import br.com.buybye.adapter.database.repository.CarrinhoRepository;
import br.com.buybye.adapter.database.repository.ItemCarrinhoRepository;
import br.com.buybye.port.CarrinhoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarrinhoUseCase implements CarrinhoPort {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ItemCarrinhoRepository itemCarrinhoRepository;

    private Float TAX_RATE =5.0F;


    @Override
    public CarrinhoEntity findById(Long idCarrinho) {
        return carrinhoRepository.findById(idCarrinho).get();
    }

    @Override
    public ItemCarrinhoEntity findCartItem(CarrinhoEntity carrinho, Long idProduto) {
        for (ItemCarrinhoEntity itemCarrinho : carrinho.getItemCarrinho()) {
            if (itemCarrinho.getProduto().getId().equals(idProduto)) {
                return itemCarrinho;
            }
        }

        return null;
    }

    @Override
    public CarrinhoEntity findShoppingCart(CompradorEntity comprador) {
        if(comprador.getCarrinho() == null){
            CarrinhoEntity carrinho = new CarrinhoEntity();

            carrinho.setMetodo_pagamento("Dinheiro ou Delivery");
            carrinho.setTipoFrete("Frete Gratis");

            carrinho.setComprador(comprador);
            carrinho.setItemCarrinho(new ArrayList<ItemCarrinhoEntity>());

            return carrinho;
        }

        return comprador.getCarrinho();
    }

    @Override
    public CarrinhoEntity addItemToCart(ProdutoEntity produto, Long qtd, CompradorEntity comprador) {
        Float subTotal = 0.0F, totalPrice = 0.0F, totalTax = 0.0F, grandTotal = 0.0F;
        long totalQty = 0;

        CarrinhoEntity carrinho = findShoppingCart(comprador);
        ItemCarrinhoEntity itemCarrinho = findCartItem(carrinho, produto.getId());
        if(itemCarrinho == null){
            itemCarrinho = new ItemCarrinhoEntity();

            itemCarrinho.setProduto(produto);
            itemCarrinho.setCarrinho(carrinho);

            //get items list and item in it
            List<ItemCarrinhoEntity> cartItemList = carrinho.getItemCarrinho();
            cartItemList.add(itemCarrinho);

            carrinho.setItemCarrinho(cartItemList);
        }

        itemCarrinho.setPreco(produto.getPrecoVenda());

        totalQty = itemCarrinho.getQuantidade() + qtd;
        itemCarrinho.setQuantidade(totalQty);

        totalPrice = produto.getPrecoVenda() * totalQty;
        itemCarrinho.setValorTotal(totalPrice);

        carrinho.setFrete(0.0F);

        subTotal = getSubTotal(carrinho);
        carrinho.setSubTotal(subTotal);

        carrinho.setTaxRate(TAX_RATE);

        totalTax = (TAX_RATE / 100) * subTotal;
        carrinho.setTaxTotal(totalTax);

        grandTotal = subTotal + totalTax;
        carrinho.setGrandTotal(grandTotal);

        carrinhoRepository.save(carrinho);

        return carrinho;
    }

    @Override
    public CarrinhoEntity removeItemFromCart(ProdutoEntity produto, CompradorEntity comprador) {
        Float subTotal = 0.0F, totalPrice = 0.0F, totalTax = 0.0F, grandTotal = 0.0F;
        long totalQty = 0;

        CarrinhoEntity shoppingCart = findShoppingCart(comprador);
        ItemCarrinhoEntity cartItem = findCartItem(shoppingCart, produto.getId());

        List<ItemCarrinhoEntity> cartItemList = shoppingCart.getItemCarrinho();
        cartItemList.remove(cartItem);

        shoppingCart.setItemCarrinho(cartItemList);

        shoppingCart.setFrete(0.0F);

        subTotal = getSubTotal(shoppingCart);
        shoppingCart.setSubTotal(subTotal);

        shoppingCart.setTaxRate(TAX_RATE);

        totalTax = (TAX_RATE / 100) * subTotal;
        shoppingCart.setTaxTotal(totalTax);

        grandTotal = subTotal + totalTax;
        shoppingCart.setGrandTotal(grandTotal);

        carrinhoRepository.save(shoppingCart);

        itemCarrinhoRepository.delete(cartItem);

        return shoppingCart;
    }

    @Override
    public CarrinhoEntity updateItemFromCart(ProdutoEntity produto, Long qtd, CompradorEntity comprador) {
        Float subTotal = 0.0F, totalPrice = 0.0F, totalTax = 0.0F, grandTotal = 0.0F;
        long totalQty = 0;

        CarrinhoEntity shoppingCart = findShoppingCart(comprador);
        ItemCarrinhoEntity cartItem = findCartItem(shoppingCart, produto.getId());

        cartItem.setPreco(produto.getPrecoVenda());

        totalQty = qtd;
        cartItem.setQuantidade(totalQty);

        totalPrice = produto.getPrecoVenda() * totalQty;
        cartItem.setValorTotal(totalPrice);

        shoppingCart.setFrete(0.0F);

        subTotal = getSubTotal(shoppingCart);
        shoppingCart.setSubTotal(subTotal);

        shoppingCart.setTaxRate(TAX_RATE);

        totalTax = (TAX_RATE / 100) * subTotal;
        shoppingCart.setTaxTotal(totalTax);

        grandTotal = subTotal + totalTax;
        shoppingCart.setGrandTotal(grandTotal);

        carrinhoRepository.save(shoppingCart);

        return shoppingCart;
    }

    @Override
    public void emptyShoppingCart(CompradorEntity comprador) {
        if(comprador == null || comprador.getCarrinho()==null) return;

        carrinhoRepository.delete(comprador.getCarrinho());
    }

    private Float getSubTotal(CarrinhoEntity carrinhoEntity){
        Float subTotal=0.0F;
        for (ItemCarrinhoEntity itemCarrinho : carrinhoEntity.getItemCarrinho()) {
            subTotal += itemCarrinho.getValorTotal();
        }
        return subTotal;
    }
}
