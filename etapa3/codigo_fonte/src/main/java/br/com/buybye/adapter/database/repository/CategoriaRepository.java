package br.com.buybye.adapter.database.repository;

import br.com.buybye.adapter.database.entities.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
    CategoriaEntity findByNome(String nome);
}
