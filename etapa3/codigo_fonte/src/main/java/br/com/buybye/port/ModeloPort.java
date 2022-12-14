package br.com.buybye.port;

import br.com.buybye.adapter.database.entities.ModeloEntity;

import java.util.List;

public interface ModeloPort {
    List<ModeloEntity> findAll();
    ModeloEntity findByName(String nome);
    List<ModeloEntity> getModels(Long idMarca);
    ModeloEntity get(long  id);
    void save(ModeloEntity modelo);
    void delete(long id);
}
