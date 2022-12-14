package br.com.buybye.domain.model;

/*
    Created By: noman azeem
    Contact: syed.noman.azeem@gmail.com
*/
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

	@NotEmpty(message = "Nome nao pode ser vazio!")
	private String nome;

	@NotEmpty(message = "Sobrenome nao pode ser vazio!")
	private String sobrenome;

	@Email(message = "*Digite um email valido!")
	private String username;

	@Length(min = 5, message = "*Sua senha deve ter pelo menos 5 caracteres")
	@NotEmpty(message = "*Digite uma senha valida")
	private String senha;
}
