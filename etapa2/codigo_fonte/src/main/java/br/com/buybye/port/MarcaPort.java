package br.com.buybye.port;

import br.com.buybye.adapter.database.entities.MarcaEntity;

import java.util.List;

public interface MarcaPort {
    List<MarcaEntity> findAll();
    MarcaEntity findByName(String nome);
    MarcaEntity get(long  id);
    void save(MarcaEntity marca);
    void delete(long id);
}
