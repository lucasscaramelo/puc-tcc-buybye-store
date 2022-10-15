package br.com.buybye.adapter.database.repository;

import br.com.buybye.adapter.database.entities.DetalhePedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalhePedidoRepository extends JpaRepository<DetalhePedidoEntity, Long> {
}
