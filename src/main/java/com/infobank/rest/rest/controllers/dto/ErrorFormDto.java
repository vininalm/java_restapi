package com.infobank.rest.rest.controllers.dto;

public class ErrorFormDto {
    private String campo;
    private String mensagem;

    public ErrorFormDto(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
