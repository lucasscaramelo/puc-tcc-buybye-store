package br.com.buybye.port;

import br.com.buybye.adapter.database.entities.UsuarioEntity;
import br.com.buybye.domain.model.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioPort extends UserDetailsService {
    UsuarioEntity save(Usuario usuario);
    UsuarioEntity findByUsername(String username);
}
