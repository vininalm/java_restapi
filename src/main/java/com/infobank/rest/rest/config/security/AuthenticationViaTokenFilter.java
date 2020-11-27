package com.infobank.rest.rest.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infobank.rest.rest.model.Usuario;
import com.infobank.rest.rest.repository.UsuarioRepository;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthenticationViaTokenFilter extends OncePerRequestFilter {
    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;

    public AuthenticationViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = retrieveToken(request);

        if (tokenService.isTokenValid(token)) {
            authenticateClient(token);
        }

        filterChain.doFilter(request, response);

    }

    private void authenticateClient(String token) {
        Long idUser = tokenService.getIdUser(token);
        Usuario usuario = usuarioRepository.findById(idUser).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String retrieveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }

}
