package br.com.buybye.domain.usecase;

import br.com.buybye.adapter.database.entities.MarcaEntity;
import br.com.buybye.adapter.database.repository.MarcaRepository;
import br.com.buybye.port.MarcaPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MarcaUseCase implements MarcaPort {

    @Autowired
    private MarcaRepository marcaRepository;

    @Override
    public List<MarcaEntity> findAll() {
        return (List<MarcaEntity>) marcaRepository.findAll();
    }

    @Override
    public MarcaEntity findByName(String nome) {
        return marcaRepository.findByName(nome);
    }

    @Override
    public MarcaEntity get(long id) {
        return marcaRepository.findById(id).get();
    }

    @Override
    public void save(MarcaEntity marca) {
        marcaRepository.save(marca);
    }

    @Override
    public void delete(long id) {
        marcaRepository.deleteById(id);
    }
}
