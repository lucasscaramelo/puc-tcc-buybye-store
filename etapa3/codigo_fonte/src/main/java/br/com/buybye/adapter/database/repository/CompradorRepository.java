package br.com.buybye.adapter.database.repository;

import br.com.buybye.adapter.database.entities.CompradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompradorRepository extends JpaRepository<CompradorEntity, Long> {
    CompradorEntity findByUsername(String username);
}
