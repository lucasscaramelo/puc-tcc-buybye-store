package br.com.buybye.domain.usecase;

import br.com.buybye.adapter.database.entities.CompradorEntity;
import br.com.buybye.adapter.database.entities.PaisEntity;
import br.com.buybye.adapter.database.entities.RegraEntity;
import br.com.buybye.adapter.database.repository.CompradorRepository;
import br.com.buybye.adapter.database.repository.PaisRepository;
import br.com.buybye.adapter.database.repository.RegraRepository;
import br.com.buybye.domain.model.Comprador;
import br.com.buybye.port.CompradorPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class CompradorUseCase implements CompradorPort {

    @Autowired
    private CompradorRepository compradorRepository;

    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private RegraRepository regraRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public CompradorUseCase() {
        super();
    }

    @Override
    public CompradorEntity save(Comprador comprador) {

        CompradorEntity compradorEntity = new CompradorEntity();
        compradorEntity.setNome(comprador.getNome());
        compradorEntity.setSobrenome(comprador.getSobrenome());
        compradorEntity.setUsername(comprador.getUsername());
        compradorEntity.setTelefone(comprador.getTelefone());
        compradorEntity.setSenha(passwordEncoder.encode(comprador.getSenha()));
        compradorEntity.setRegras(Arrays.asList(regraRepository.findByName("CUSTOMER")));

        PaisEntity pais = paisRepository.findByName(comprador.getPais());

        compradorEntity.setEmpresa(comprador.getEmpresa());
        compradorEntity.setEndereco1(comprador.getEndereco1());
        compradorEntity.setEndereco2(comprador.getEndereco2());
        compradorEntity.setCep(comprador.getCep());
        compradorEntity.setCidade(comprador.getCidade());
        compradorEntity.setPais(pais);
        compradorEntity.setEstado(comprador.getEstado());

        return compradorRepository.save(compradorEntity);
    }

    @Override
    public CompradorEntity save(CompradorEntity comprador) {
        return compradorRepository.save(comprador);
    }

    @Override
    public CompradorEntity findByUsername(String username) {
        return compradorRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CompradorEntity comprador = compradorRepository.findByUsername(username);
        if(comprador == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(comprador.getUsername(), comprador.getSenha()
                , mapRolesToAuthorities(comprador.getRegras()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RegraEntity> regras){
        return regras.stream().map(regra -> new SimpleGrantedAuthority(regra.getNome())).collect(Collectors.toList());
    }
}
