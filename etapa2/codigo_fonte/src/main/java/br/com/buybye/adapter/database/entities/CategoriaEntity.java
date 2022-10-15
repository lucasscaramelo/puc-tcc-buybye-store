package br.com.buybye.adapter.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="categoria")
public class CategoriaEntity {

    public CategoriaEntity(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
        this.status = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long id;

    @Column(name = "nome")
    @NotEmpty(message = "Nome nao pode ser vazio!")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "status")
    private Boolean status;

}
