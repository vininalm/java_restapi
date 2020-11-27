package com.infobank.rest.rest.controllers.dto;

public class TokenDto {
    String token;
    String tipo;

    public TokenDto(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getToken() {
        return token;
    }
}
