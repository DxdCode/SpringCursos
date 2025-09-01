package com.spring.cursoapis.repository;


import com.spring.cursoapis.entity.Curso;
import com.spring.cursoapis.entity.EstadoCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByNombreCurso(String nombreCurso);
    Optional<Curso> findByIdCurso(Long idCurso);
    List<Curso> findAllByEstadoCurso(EstadoCurso estadoCurso);
}
