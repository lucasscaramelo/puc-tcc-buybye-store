package br.com.buybye.adapter.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="marca")
public class MarcaEntity {

    public MarcaEntity(String nome){
        this.nome = nome;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marca")
    private Long id;

    @Column(name = "nome")
    @NotEmpty(message = "Nome nao pode ser vazio!")
    private String nome;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "marca")
    private List<ModeloEntity> modeloEntityList;

    @Override
    public String toString() {
        return "Marca{" +
                "id=" + id +
                ", nome ='" + nome + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return 42;
    }
}
