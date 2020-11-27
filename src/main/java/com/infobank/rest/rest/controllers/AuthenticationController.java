package com.infobank.rest.rest.controllers;

import javax.validation.Valid;

import com.infobank.rest.rest.config.security.TokenService;
import com.infobank.rest.rest.controllers.dto.TokenDto;
import com.infobank.rest.rest.controllers.form.LoginAuthForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginAuthForm loginForm) {
        UsernamePasswordAuthenticationToken loginData = loginForm.convert();

        try {

            // o metodo authenticate olha as nossas configs e vai na nossa AutenticationService pra
            // validar os dados
            Authentication authentication = authManager.authenticate(loginData);
            String token = tokenService.generateToken(authentication);
            return ResponseEntity.ok().body(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
