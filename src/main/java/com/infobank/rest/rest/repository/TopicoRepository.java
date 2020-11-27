package com.infobank.rest.rest.repository;
import com.infobank.rest.rest.model.Topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
	public Page<Topico> findByCursoNome(String nomeCurso, Pageable pageable);
}
