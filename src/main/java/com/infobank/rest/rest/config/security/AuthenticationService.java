package com.infobank.rest.rest.config.security;

import java.util.Optional;

import com.infobank.rest.rest.model.Usuario;
import com.infobank.rest.rest.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if(usuario.isPresent()) {
            return usuario.get();
        } else {
            throw new UsernameNotFoundException("Invalid data");
        }
        
    }
    
}
