package com.infobank.rest.rest.repository;

import com.infobank.rest.rest.model.Curso;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    public Curso findByNome(String nomeCurso);
}
