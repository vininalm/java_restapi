package com.infobank.rest.rest.controllers.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.infobank.rest.rest.model.Topico;

public class TopicoUpdateForm {

    @NotNull @NotEmpty
    public String titulo;
    @NotNull @NotEmpty
    public String mensagem;

    public void update(Topico topico) {
        topico.setMensagem(this.mensagem);
        topico.setTitulo(this.titulo);
    }

    public void setMessagem(String messagem) {
        this.mensagem = messagem;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
