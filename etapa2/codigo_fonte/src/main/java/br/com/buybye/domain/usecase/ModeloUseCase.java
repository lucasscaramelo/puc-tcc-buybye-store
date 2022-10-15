package br.com.buybye.domain.usecase;

import br.com.buybye.adapter.database.entities.ModeloEntity;
import br.com.buybye.adapter.database.repository.ModeloRepository;
import br.com.buybye.port.ModeloPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ModeloUseCase implements ModeloPort {

    @Autowired
    private ModeloRepository modelRepository;

    @Override
    public List<ModeloEntity> findAll() {
        return (List<ModeloEntity>) modelRepository.findAll();
    }

    @Override
    public ModeloEntity findByName(String nome) {
        return modelRepository.findByName(nome);
    }

    @Override
    public List<ModeloEntity> getModels(Long idMarca) {
        return modelRepository.getModels(idMarca);
    }

    @Override
    public ModeloEntity get(long id) {
        return modelRepository.findById(id).get();
    }

    @Override
    public void save(ModeloEntity modelo) {
        modelRepository.save(modelo);
    }

    @Override
    public void delete(long id) {
        modelRepository.deleteById(id);
    }
}
