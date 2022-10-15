package br.com.buybye.port;

import br.com.buybye.adapter.database.entities.PaisEntity;

import java.util.List;

public interface PaisPort {
    List<PaisEntity> findAll();
    PaisEntity findByName(String name);
    PaisEntity get(long  id);
    void save(PaisEntity pais);
    void delete(long id);
}
