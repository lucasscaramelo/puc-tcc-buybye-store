package br.com.buybye.port;

import br.com.buybye.adapter.database.entities.CompradorEntity;
import br.com.buybye.domain.model.Comprador;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CompradorPort extends UserDetailsService {
    CompradorEntity save(Comprador comprador);// insert
    CompradorEntity save(CompradorEntity comprador);// update
    CompradorEntity findByUsername(String username);
}
