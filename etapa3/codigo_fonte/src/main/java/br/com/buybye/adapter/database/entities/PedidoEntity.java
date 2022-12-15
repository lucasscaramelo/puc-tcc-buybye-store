package br.com.buybye.adapter.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="pedido")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comprador", referencedColumnName = "id_comprador")
    private CompradorEntity comprador;

    @Column(name ="data_pedido")
    private Date dataPedido;

    @Column(name ="data_entrega")
    private Date dataEntrega;

    @Column(name = "sub_total")
    private Float subTotal;

    @Column(name = "frete")
    private Float frete;

    @Column(name = "tax_rate")
    private Float taxRate;

    @Column(name = "tax_total")
    private Float taxTotal;

    @Column(name = "grand_total")
    private Float grandTotal;

    @Column(name = "status_pedido")
    private String status;

    @Column(name = "metodo_entrega")
    private String metodoEntrega;

    @Column(name = "metodo_pagamento")
    private String metodoPagamento;

    @Column(name = "descricao")
    private String descricao;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private List<DetalhePedidoEntity> detalhePedido;

}
