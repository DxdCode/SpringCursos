package com.spring.cursoapis.service;

import com.spring.cursoapis.entity.Curso;
import com.spring.cursoapis.entity.EstadoCurso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    Curso registrarCurso(Curso curso);

    List<Curso> registrarVariosCuros(List<Curso> curso);


    List<Curso> listarCursos();

    Optional<Curso> BuscarPorNombre(String nombreCurso);

    Optional<Curso> BuscarPorId(Long idCurso);

    Curso actualizarCurso(Long idCurso,Curso curso) throws Exception;

    void eliminarCurso(Long idCurso);

    Curso cambiarEstadoCurso(Long idCurso, EstadoCurso nuevoEstadoCurso);

    List<Curso> listarCursosActivos(EstadoCurso estadoCurso);





}
