package br.com.buybye.adapter.database.repository;

import br.com.buybye.adapter.database.entities.CarrinhoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoRepository extends JpaRepository<CarrinhoEntity, Long> {
}
