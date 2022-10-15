package br.com.buybye.adapter.database.repository;

import br.com.buybye.adapter.database.entities.MarcaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<MarcaEntity, Long> {
    MarcaEntity findByName(String nome);
}
