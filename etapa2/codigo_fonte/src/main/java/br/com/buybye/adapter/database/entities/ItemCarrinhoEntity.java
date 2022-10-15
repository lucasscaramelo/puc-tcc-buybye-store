package br.com.buybye.adapter.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="item_carrinho")
public class ItemCarrinhoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item_carrinho")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_carrinho", referencedColumnName = "id_carrinho")
    private CarrinhoEntity carrinho;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_produto", referencedColumnName = "id_produto")
    private ProdutoEntity produto;

    @Column(name = "quantidade")
    private Long quantidade=0L;

    @Column(name = "preco")
    private Float preco=0.0F;

    @Column(name = "valor_total")
    private Float valorTotal=0.0F;

}
