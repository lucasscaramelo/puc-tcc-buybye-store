package br.com.buybye.domain.utils;

import br.com.buybye.adapter.database.entities.*;
import br.com.buybye.adapter.database.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class SetupData implements ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;

    @Autowired
    private RegraRepository roleRepository;

    @Autowired
    private CategoriaRepository categoryRepository;

    @Autowired
    private PaisRepository countryRepository;

    @Autowired
    private MarcaRepository makeRepository;

    @Autowired
    private ModeloRepository modelRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (alreadySetup)
            return;

        //Setup regras
        createRoleIfNotFound("ADMIN");
        createRoleIfNotFound("COMPRADOR");

        //Setup categorias
        createCategoryIfNotFound(new CategoriaEntity("Livros", "Livro desc"));

        //Setup pais
        createCountryIfNotFound(new PaisEntity("BRA", "Brasil"));

        //Setup marca
        MarcaEntity allMake = createMakeIfNotFound(new MarcaEntity("Todas as marcas"));

        //Setup modelo
        createModelIfNotFound(new ModeloEntity("Todos os modelos", allMake));

        //Setup Usuario
       // createUserAdminIfNotFound(new UsuarioEntity("Buybye", "Admin", "admin@buybye.com.br", "admin123"));
    }

    @Transactional
    RegraEntity createRoleIfNotFound(String name) {
        RegraEntity role = roleRepository.findByNome(name);
        if (role == null) {
            role = new RegraEntity(name);
            roleRepository.save(role);
            role = roleRepository.findByNome(name);
        }

        return role;
    }

    @Transactional
    CategoriaEntity createCategoryIfNotFound(CategoriaEntity category) {
        CategoriaEntity category1 = categoryRepository.findByNome(category.getNome());
        if (category1 == null) {
            categoryRepository.save(category);
            category1 = categoryRepository.findByNome(category.getNome());
        }

        return category1;
    }

    @Transactional
    PaisEntity createCountryIfNotFound(PaisEntity country) {
        PaisEntity country1 = countryRepository.findByNome(country.getNome());
        if (country1 == null) {
            countryRepository.save(country);
            country1 = countryRepository.findByNome(country.getNome());
        }

        return country1;
    }

    @Transactional
    MarcaEntity createMakeIfNotFound(MarcaEntity make) {
        MarcaEntity make1 = makeRepository.findByNome(make.getNome());
        if (make1 == null) {
            makeRepository.save(make);
            make1 = makeRepository.findByNome(make.getNome());
        }

        return make1;
    }

    @Transactional
    ModeloEntity createModelIfNotFound(ModeloEntity model) {
        ModeloEntity model1 = modelRepository.findByName2(model.getNome(), model.getMarca().getId());
        if (model1 == null) {
            modelRepository.save(model);
            model1 = modelRepository.findByName2(model.getNome(), model.getMarca().getId());
        }

        return model1;
    }

    @Transactional
    UsuarioEntity createUserAdminIfNotFound(UsuarioEntity user) {
        UsuarioEntity user1 = usuarioRepository.findByUsername(user.getUsername());
        if (user1 == null) {
            usuarioRepository.save(user);
            user1 = usuarioRepository.findByUsername(user.getUsername());
        }

        return user1;
    }
}
