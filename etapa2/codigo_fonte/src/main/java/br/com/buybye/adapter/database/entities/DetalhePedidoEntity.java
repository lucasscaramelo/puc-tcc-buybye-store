package br.com.buybye.adapter.database.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="detalhe_pedido")
public class DetalhePedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalhe_pedido")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido")
    private PedidoEntity pedido;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produto", referencedColumnName = "id_produto")
    private ProdutoEntity produto;

    @Column(name = "quantidade")
    private Long quantidade;

    @Column(name = "preco")
    private Float preco;

    @Column(name = "valor_total")
    private Float valorTotal;

}
