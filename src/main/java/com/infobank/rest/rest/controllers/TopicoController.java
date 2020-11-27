package com.infobank.rest.rest.controllers;

import java.net.URI;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.infobank.rest.rest.controllers.dto.TopicoDetalhesDto;
import com.infobank.rest.rest.controllers.dto.TopicoDto;
import com.infobank.rest.rest.controllers.form.TopicoForm;
import com.infobank.rest.rest.controllers.form.TopicoUpdateForm;
import com.infobank.rest.rest.model.Topico;
import com.infobank.rest.rest.repository.CursoRepository;
import com.infobank.rest.rest.repository.TopicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    TopicoRepository topicoRepository;
    @Autowired
    CursoRepository cursoRepository;

    @GetMapping
    @Cacheable(value = "listaTopicos")
    public Page<TopicoDto> sendTopicos(@RequestParam(required = false) String nomeCurso, Pageable pageable) {
        if (nomeCurso == null) {
            return TopicoDto.convert(topicoRepository.findAll(pageable));
        } else {
            return TopicoDto.convert(topicoRepository.findByCursoNome(nomeCurso, pageable));
        }
    }

    @PostMapping
    @Transactional
    @CacheEvict(value =  "listaTopicos", allEntries = true  )
    public ResponseEntity<TopicoDto> createNewTopico(@RequestBody @Valid TopicoForm topicoForm,
            UriComponentsBuilder uriBuilder) {
        Topico topico = topicoForm.toTopico(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> sendTopicoById(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isPresent()) {
            return ResponseEntity.ok().body(new TopicoDetalhesDto(topicoOptional.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value =  "listaTopicos", allEntries = true)
    public ResponseEntity<?> updateTopico(@PathVariable Long id,
            @Valid @RequestBody TopicoUpdateForm topicoUpdateForm) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isPresent()) {
            topicoUpdateForm.update(topicoOptional.get());
            return ResponseEntity.ok().body(new TopicoDto(topicoOptional.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value =  "listaTopicos", allEntries = true)
    public ResponseEntity<?> deleteTopico(@PathVariable Long id) {

        if (topicoRepository.findById(id).isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
