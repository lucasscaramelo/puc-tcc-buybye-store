package br.com.buybye.domain.usecase;

import br.com.buybye.adapter.database.entities.DetalhePedidoEntity;
import br.com.buybye.adapter.database.repository.DetalhePedidoRepository;
import br.com.buybye.port.DetalhePedidoPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DetalhePedidoUseCase implements DetalhePedidoPort {

    @Autowired
    private DetalhePedidoRepository orderDetailRepository;

    @Override
    public List<DetalhePedidoEntity> findAll() {
        return (List<DetalhePedidoEntity>) orderDetailRepository.findAll();
    }

    @Override
    public DetalhePedidoEntity get(long id) {
        return orderDetailRepository.findById(id).get();
    }

    @Override
    public void save(DetalhePedidoEntity order) {
        orderDetailRepository.save(order);
    }

    @Override
    public void delete(long id) {
        orderDetailRepository.deleteById(id);
    }
}
