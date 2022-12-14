package br.com.buybye.domain.usecase;

import br.com.buybye.adapter.database.entities.CategoriaEntity;
import br.com.buybye.adapter.database.repository.CategoriaRepository;
import br.com.buybye.port.CategoriaPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoriaUseCase implements CategoriaPort {

    @Autowired
    private CategoriaRepository categoryRepository;

    @Override
    public List<CategoriaEntity> findAll() {
        return (List<CategoriaEntity>) categoryRepository.findAll();
    }

    @Override
    public CategoriaEntity findByName(String nome) {
        return categoryRepository.findByNome(nome);
    }

    @Override
    public CategoriaEntity get(long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public void save(CategoriaEntity categoria) {
        categoryRepository.save(categoria);
    }

    @Override
    public void delete(long id) {
        categoryRepository.deleteById(id);
    }
}
