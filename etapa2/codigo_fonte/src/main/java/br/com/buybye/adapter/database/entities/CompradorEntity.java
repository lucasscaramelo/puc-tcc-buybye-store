package br.com.buybye.adapter.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comprador", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class CompradorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comprador")
    private Long id;

    @NotEmpty(message = "Nome nao pode ser vazio!")
    @Column(name = "nome")
    private String nome;

    @NotEmpty(message = "Sobrenome nao pode ser vazio!")
    @Column(name = "sobrenome")
    private String sobrenome;

    @Column(name = "username")
    @Email(message = "*Digite um email valido")
    @NotEmpty(message = "*Digite um email valido")
    private String username;

    @Column(name = "senha")
    @Length(min = 5, message = "*Sua senha deve ter pelo menos 5 caracteres")
    @NotEmpty(message = "*Digite uma senha valida")
    private String senha;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "empresa")
    private String empresa;

    @NotNull(message = "Digite o endereco 1!")
    @Column(name = "endereco1")
    private String endereco1;

    @Column(name = "endereco2")
    private String endereco2;

    @NotNull(message = "Digite a cidade!")
    @Column(name = "cidade")
    private String cidade;

    @NotNull(message = "Digite o estado!")
    @Column(name = "estado")
    private String estado;

    @NotNull(message = "Digite o cep!")
    @Column(name = "cep")
    private String cep;

    @NotNull(message = "Selecione o pais!")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais")
    private PaisEntity pais;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "comprador_regras",
            joinColumns = @JoinColumn(
                    name = "id_comprador", referencedColumnName = "id_comprador"),
            inverseJoinColumns = @JoinColumn(
                    name = "id_regra", referencedColumnName = "id_regra"))
    private Collection<RegraEntity> regras;

    @OneToOne(mappedBy = "comprador")
    private CarrinhoEntity carrinho;

    @OneToMany(mappedBy = "comprador")
    private List<PedidoEntity> pedido;

    public String getNomeCompleto(){
        return nome +" "+ sobrenome;
    }

    @Override
    public int hashCode() {
        return 42;
    }

}
