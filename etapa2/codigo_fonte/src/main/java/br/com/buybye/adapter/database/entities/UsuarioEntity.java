package br.com.buybye.adapter.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @NotEmpty(message = "O nome nao pode ser vazio")
    @Column(name = "nome")
    private String nome;

    @NotEmpty(message = "O sobrenome nao pode ser vazio")
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

    @Column(name = "status")
    private Boolean status;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuarios_regras",
            joinColumns = @JoinColumn(
                    name = "id_usuario", referencedColumnName = "id_usuario"),
            inverseJoinColumns = @JoinColumn(
                    name = "id_regra", referencedColumnName = "id_regra"))
    private Collection<RegraEntity> regras;
}
