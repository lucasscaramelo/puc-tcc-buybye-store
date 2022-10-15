package br.com.buybye.adapter.database.repository;

import br.com.buybye.adapter.database.entities.RegraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegraRepository extends JpaRepository<RegraEntity, Long> {
    RegraEntity findByName(String name);
}
