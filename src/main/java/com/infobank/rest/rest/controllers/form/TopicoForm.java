package com.infobank.rest.rest.controllers.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.infobank.rest.rest.model.Topico;
import com.infobank.rest.rest.repository.CursoRepository;

public class TopicoForm {
    @NotBlank @NotNull 
    private String titulo;
    @NotBlank @NotNull 
    private String mensagem;
    @NotBlank @NotNull 
    private String nomeCurso;

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Topico toTopico(CursoRepository cursoRepository) {
        return new Topico(this.titulo, this.mensagem, cursoRepository.findByNome(this.nomeCurso));
    }
}
