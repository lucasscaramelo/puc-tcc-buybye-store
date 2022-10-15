package br.com.buybye.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comprador {

	@NotEmpty(message = "Nome nao pode ser vazio!")
	private String nome;

	@NotEmpty(message = "Sobrenome nao pode ser vazio!")
	private String sobrenome;

	@NotEmpty(message = "Email nao pode ser vazio!")
	@Email(message = "*Digite um email valido!")
	private String username;

	@Length(min = 5, message = "*Sua senha deve ter pelo menos 5 caracteres")
	@NotEmpty(message = "*Digite uma senha valida")
	private String senha;

	@NotEmpty(message = "*Digite seu telefone")
	private String telefone;

	@NotEmpty(message = "Confirmacao senha")
	private String confirm;

	private String empresa;
	private String endereco1;
	private String endereco2;
	private String cidade;
	private String estado;
	private String pais;
	private String cep;
}
