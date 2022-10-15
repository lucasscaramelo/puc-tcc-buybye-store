package br.com.buybye.adapter.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="modelo")
public class ModeloEntity {

    public ModeloEntity(String nome, MarcaEntity marca){
        this.nome = nome;
        this.marca = marca;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modelo")
    private Long id;

    @NotNull(message = "Selecione a marca!")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_marca", referencedColumnName = "id_marca")
    private MarcaEntity marca;

    @Column(name = "nome")
    @NotEmpty(message = "Nome nao pode ser vazio!")
    private String nome;

    @Override
    public String toString() {
        return "Modelo{" +
                "id=" + id +
                ", marca=" + marca +
                ", nome='" + nome + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return 42;
    }
}
