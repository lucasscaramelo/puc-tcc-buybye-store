package br.com.buybye.adapter.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="carrinho")
public class CarrinhoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrinho")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_comprador", referencedColumnName = "id_comprador")
    private CompradorEntity comprador;

    @Column(name = "sub_total")
    private Float subTotal;

    @Column(name = "tax_rate")
    private Float taxRate;

    @Column(name = "tax_total")
    private Float taxTotal;

    @Column(name = "grand_total")
    private Float grandTotal;

    @Column(name = "frete")
    private Float frete;

    @Column(name = "tipo_frete")
    private String tipoFrete;

    @Column(name = "metodo_pagamento")
    private String metodo_pagamento;

    @Column(name = "descricao")
    private String descricao;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carrinho")
    private List<ItemCarrinhoEntity> itemCarrinho;

}
