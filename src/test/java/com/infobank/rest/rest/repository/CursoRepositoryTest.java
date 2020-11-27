package com.infobank.rest.rest.repository;

import com.infobank.rest.rest.model.Curso;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CursoRepositoryTest {

    @Autowired
    private CursoRepository repository;

    @Test
    public void shouldLoadCourseBasedOnItsTitle() {
        String nomeCurso = "Spring Boot";
        Curso curso = repository.findByNome(nomeCurso);

        Assert.assertNotNull(curso);
        Assert.assertEquals(nomeCurso, curso.getNome());
    }

    @Test
    public void shouldNotLoadCourseIfTitleDoesntExist() {
        String nomeCurso = "JPA";
        Curso curso = repository.findByNome(nomeCurso);

        Assert.assertNull(curso);
    }
}
