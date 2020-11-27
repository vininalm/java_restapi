package com.infobank.rest.rest.controllers;

import com.infobank.rest.rest.model.Usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/name") 
    public ResponseEntity<?> retrieveUserName(@AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok().body(usuario.getNome());
    }
    
}
