package br.com.buybye.domain.usecase;

import br.com.buybye.adapter.database.entities.PaisEntity;
import br.com.buybye.adapter.database.repository.PaisRepository;
import br.com.buybye.port.PaisPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaisUseCase implements PaisPort {

    @Autowired
    private PaisRepository countryRepository;

    @Override
    public List<PaisEntity> findAll() {
        return (List<PaisEntity>) countryRepository.findAll();
    }

    @Override
    public PaisEntity findByName(String name) {
        return countryRepository.findByNome(name);
    }

    @Override
    public PaisEntity get(long id) {
        return countryRepository.findById(id).get();
    }

    @Override
    public void save(PaisEntity pais) {
        countryRepository.save(pais);
    }

    @Override
    public void delete(long id) {
        countryRepository.deleteById(id);
    }
}
