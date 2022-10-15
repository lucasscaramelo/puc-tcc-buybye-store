package br.com.buybye.port;

import br.com.buybye.adapter.database.entities.CategoriaEntity;

import java.util.List;

public interface CategoriaPort {

    List<CategoriaEntity> findAll();
    CategoriaEntity findByName(String nome);
    CategoriaEntity get(long  id);
    void save(CategoriaEntity categoria);
    void delete(long id);
}
