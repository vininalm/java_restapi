package com.infobank.rest.rest.controllers.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import com.infobank.rest.rest.model.StatusTopico;
import com.infobank.rest.rest.model.Topico;

public class TopicoDetalhesDto {
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private StatusTopico statusTopico;
    private String cursoNome;
    private List<RespostaDto> respostas;

    public TopicoDetalhesDto(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
        this.statusTopico = topico.getStatus();
        this.cursoNome = topico.getCurso().getNome();
        this.respostas = topico.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList());
    }

    public String getCursoNome() {
        return cursoNome;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Long getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public List<RespostaDto> getRespostas() {
        return respostas;
    }

    public StatusTopico getStatusTopico() {
        return statusTopico;
    }

    public String getTitulo() {
        return titulo;
    }

}
