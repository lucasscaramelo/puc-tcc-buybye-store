package br.com.buybye.adapter.database.repository;

import br.com.buybye.adapter.database.entities.ItemCarrinhoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinhoEntity, Long> {
}
