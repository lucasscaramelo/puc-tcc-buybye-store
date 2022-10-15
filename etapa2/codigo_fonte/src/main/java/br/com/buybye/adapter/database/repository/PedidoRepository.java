package br.com.buybye.adapter.database.repository;

import br.com.buybye.adapter.database.entities.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
}
