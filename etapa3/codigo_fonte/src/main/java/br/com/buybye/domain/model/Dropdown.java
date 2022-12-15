package br.com.buybye.domain.model;

import lombok.Data;

@Data
public class Dropdown {

    public Dropdown(Long id, String nome){
        this.id = id;
        this.nome = nome;
    }
    private Long id;
    private String nome;
}
