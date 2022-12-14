package br.com.buybye.domain.usecase;

import br.com.buybye.adapter.database.entities.*;
import br.com.buybye.adapter.database.repository.PedidoRepository;
import br.com.buybye.port.PedidoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PedidoUseCase implements PedidoPort {

    @Autowired
    private PedidoRepository orderRepository;

    @Override
    public List<PedidoEntity> findAllOrders(CompradorEntity customer) {
        return customer.getPedido();
    }

    @Override
    public PedidoEntity saveOrder(CarrinhoEntity shoppingCart) {
        PedidoEntity order = new PedidoEntity();
        order.setComprador(shoppingCart.getComprador());
        order.setDataPedido(new Date());
        order.setDataEntrega(new Date());
        order.setSubTotal(shoppingCart.getSubTotal());
        order.setFrete(shoppingCart.getFrete());
        order.setTaxRate(shoppingCart.getTaxRate());
        order.setTaxTotal(shoppingCart.getTaxTotal());
        order.setGrandTotal(shoppingCart.getGrandTotal());
        order.setMetodoEntrega(shoppingCart.getTipoFrete());
        order.setMetodoPagamento(shoppingCart.getMetodo_pagamento());
        order.setStatus("Pending");
        order.setDescricao(shoppingCart.getDescricao());

        List<DetalhePedidoEntity> orderDetailList = new ArrayList<DetalhePedidoEntity>();
        for(ItemCarrinhoEntity cartItem : shoppingCart.getItemCarrinho()){
            DetalhePedidoEntity orderDetail = new DetalhePedidoEntity();

            orderDetail.setPedido(order);
            orderDetail.setProduto(cartItem.getProduto());
            orderDetail.setPreco(cartItem.getPreco());
            orderDetail.setQuantidade(cartItem.getQuantidade());
            orderDetail.setValorTotal(cartItem.getValorTotal());

            orderDetailList.add(orderDetail);
        }

        order.setDetalhePedido(orderDetailList);

        orderRepository.save(order);

        return order;
    }

    @Override
    public PedidoEntity get(long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public void delete(long id) {
        orderRepository.deleteById(id);
    }
}
