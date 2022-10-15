package br.com.buybye.domain.usecase;

import br.com.buybye.adapter.database.entities.RegraEntity;
import br.com.buybye.adapter.database.entities.UsuarioEntity;
import br.com.buybye.adapter.database.repository.RegraRepository;
import br.com.buybye.adapter.database.repository.UsuarioRepository;
import br.com.buybye.domain.model.Usuario;
import br.com.buybye.port.UsuarioPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class UsuarioUseCase implements UsuarioPort {

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private RegraRepository regraRepository;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;


    public UsuarioUseCase() {
        super();
    }

    @Override
    public UsuarioEntity save(Usuario usuario) {
        UsuarioEntity user = new UsuarioEntity();
        user.setNome(usuario.getNome());
        user.setSobrenome(usuario.getSobrenome());
        user.setUsername(usuario.getUsername());
        user.setSenha(passwordEncoder.encode(usuario.getSenha()));
        user.setRegras(Arrays.asList(regraRepository.findByName("ADMIN")));
        user.setStatus(true);

        return userRepository.save(user);
    }

    @Override
    public UsuarioEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UsuarioEntity user = userRepository.findByUsername(s);
        if(user == null) {
            throw new UsernameNotFoundException("Username ou senha invalidos");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getSenha()
                , mapRolesToAuthorities(user.getRegras()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RegraEntity> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNome())).collect(Collectors.toList());
    }
}
