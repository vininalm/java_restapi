package com.infobank.rest.rest.controllers.dto;

import java.time.LocalDateTime;


import com.infobank.rest.rest.model.Topico;

import org.springframework.data.domain.Page;

public class TopicoDto {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;

    public TopicoDto(Topico topico) {
        this.id = topico.getId();
        this.dataCriacao = topico.getDataCriacao();
        this.mensagem = topico.getMensagem();
        this.titulo = topico.getTitulo();
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

    public String getTitulo() {
        return titulo;
    }

	public static Page<TopicoDto> convert(Page<Topico> topicos) {
        return topicos.map(TopicoDto::new);
	}
}
