package br.com.buybye.adapter.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="produto")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long id;

    @NotNull(message = "Selecione a categoria!")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    private CategoriaEntity categoria;

    @NotNull(message = "Selecione a marca!")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_marca", nullable = true, referencedColumnName = "id_marca")
    private MarcaEntity marca;

    @NotNull(message = "Selecione o modelo!")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_modelo", referencedColumnName = "id_modelo")
    private ModeloEntity modelo;

    @NotEmpty(message = "Codigo do produto nao pode ser vazio!")
    @Column(name = "codigo_produto")
    private String codProduto;

    @NotEmpty(message = "Nome nao pode ser vazio!")
    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao", columnDefinition = "text")
    private String descricao;

    @Column(name = "preco_custo")
    private float precoCusto;

    @Column(name = "preco_venda")
    private float precoVenda;

    @Column(name = "preco_promo")
    private float precoPromo;

    @Column(name = "qtd_estoque")
    private Integer estoque;

    @Column(name = "imagem")
    private String imagem;

    @Transient
    private MultipartFile imagem_carregada;

    @Column(name = "status")
    private Boolean status;

    public String getImagemUrl(){
        if(id != null && imagem !=null) {
            return "/upload/product/" + id + "/" + imagem;
        }else {
            return "/upload/no_preview.jpg";
        }
    }
}
