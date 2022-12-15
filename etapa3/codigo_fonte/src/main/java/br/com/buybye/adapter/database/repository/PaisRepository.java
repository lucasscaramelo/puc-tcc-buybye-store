package br.com.buybye.adapter.database.repository;

import br.com.buybye.adapter.database.entities.PaisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepository extends JpaRepository<PaisEntity, Long> {
    PaisEntity findByNome(String nome);
}
