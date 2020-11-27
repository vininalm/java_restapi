package com.infobank.rest.rest.controllers.dto;

import com.infobank.rest.rest.model.Resposta;

public class RespostaDto {

    private String mensagem;
    private Long id;

    public RespostaDto(Resposta resposta) {
        this.id = resposta.getId();
        this.mensagem = resposta.getMensagem();

    }

    public Long getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }

}
