package br.com.buybye.adapter.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="pais")
public class PaisEntity {

    public PaisEntity(String codigo, String nome){
        this.codigo = codigo;
        this.nome = nome;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pais")
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nome")
    private String nome;

    @Override
    public String toString() {
        return "Pais{" +
                "id=" + id +
                ", cod='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return 42;
    }
}
